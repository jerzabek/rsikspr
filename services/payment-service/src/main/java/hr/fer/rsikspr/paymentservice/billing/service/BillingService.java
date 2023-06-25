package hr.fer.rsikspr.paymentservice.billing.service;

import hr.fer.rsikspr.paymentservice.billing.model.BillDTO;
import hr.fer.rsikspr.paymentservice.billing.model.SetBillPaidDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import static hr.fer.rsikspr.paymentservice.common.Constants.USER_AUTHENTICATION_HEADER;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "PaymentService-BillingService")
public class BillingService {

  private final RestTemplate restTemplate;

  @Value("${billing-service.api-url}")
  private String billingServiceApiURL;

  public BillDTO fetchAndValidateBill(int billId, String user) {
    String getBillEndpoint = billingServiceApiURL + "/" + billId;

    HttpHeaders headers = new HttpHeaders();

    headers.add(USER_AUTHENTICATION_HEADER, user);

    HttpEntity<String> entity = new HttpEntity<>(headers);
    ResponseEntity<BillDTO> responseEntity;

    try {
      responseEntity = restTemplate
              .exchange(getBillEndpoint, HttpMethod.GET, entity, BillDTO.class);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (responseEntity.getStatusCode() != HttpStatus.OK) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    BillDTO bill = responseEntity.getBody();

    if (bill == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    if (!bill.getAuthor().equals(user)) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

    return bill;
  }


  public void setBillPaid(int billId, int receiptId, String user) {
    String getConversationEndpoint = billingServiceApiURL + "/" + billId + "/paid";

    SetBillPaidDTO body = new SetBillPaidDTO();
    body.setReceiptId(receiptId);

    HttpHeaders headers = new HttpHeaders();

    headers.add(USER_AUTHENTICATION_HEADER, user);
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<SetBillPaidDTO> entity = new HttpEntity<>(body, headers);
    ResponseEntity<Void> responseEntity;

    try {
      responseEntity = restTemplate.exchange(getConversationEndpoint, HttpMethod.PATCH, entity, Void.class);
    } catch (Exception e) {
      log.error("Error while setting bill paid", e);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (responseEntity.getStatusCode() != HttpStatus.OK) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

}
