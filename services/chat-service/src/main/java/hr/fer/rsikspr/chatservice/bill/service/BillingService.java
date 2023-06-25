package hr.fer.rsikspr.chatservice.bill.service;

import hr.fer.rsikspr.chatservice.bill.model.CreateBillDTO;
import hr.fer.rsikspr.chatservice.bill.model.SetBillPendingDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import static hr.fer.rsikspr.chatservice.common.Constants.USER_AUTHENTICATION_HEADER;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "ChatServiceBillingService")
public class BillingService {

  private final RestTemplate restTemplate;

  @Value("${billing-service.api-url}")
  private String billingServiceApiURL;

  public void createBill(String user, int conversationId, double rate) {
    String getConversationEndpoint = billingServiceApiURL + "/new";

    CreateBillDTO body = new CreateBillDTO();
    body.setRate(rate);
    body.setConversationId(conversationId);

    HttpHeaders headers = new HttpHeaders();

    headers.add(USER_AUTHENTICATION_HEADER, user);
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<CreateBillDTO> entity = new HttpEntity<>(body, headers);
    ResponseEntity<Void> responseEntity;

    try {
      responseEntity = restTemplate.exchange(getConversationEndpoint, HttpMethod.POST, entity, Void.class);
    } catch (Exception e) {
      log.error("Error while creating bill", e);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (responseEntity.getStatusCode() != HttpStatus.OK) {
      log.error("Status is not OK when creating bill");
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  public void setBillPending(String user, int conversationId, int messageCount) {
    String getConversationEndpoint = billingServiceApiURL + "/" + conversationId + "/pending";

    SetBillPendingDTO body = new SetBillPendingDTO();
    body.setCount(messageCount);

    HttpHeaders headers = new HttpHeaders();

    headers.add(USER_AUTHENTICATION_HEADER, user);
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<SetBillPendingDTO> entity = new HttpEntity<>(body, headers);
    ResponseEntity<Void> responseEntity;

    try {
      responseEntity = restTemplate.exchange(getConversationEndpoint, HttpMethod.PATCH, entity, Void.class);
    } catch (Exception e) {
      log.error("Error while setting bill pending", e);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (responseEntity.getStatusCode() != HttpStatus.OK) {
      log.error("Status is not OK when setting bill pending");
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

}
