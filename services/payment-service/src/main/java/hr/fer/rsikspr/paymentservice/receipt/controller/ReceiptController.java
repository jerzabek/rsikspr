package hr.fer.rsikspr.paymentservice.receipt.controller;

import hr.fer.rsikspr.paymentservice.billing.model.BillDTO;
import hr.fer.rsikspr.paymentservice.billing.model.BillState;
import hr.fer.rsikspr.paymentservice.billing.service.BillingService;
import hr.fer.rsikspr.paymentservice.receipt.model.PaymentDTO;
import hr.fer.rsikspr.paymentservice.receipt.model.ReceiptDAO;
import hr.fer.rsikspr.paymentservice.receipt.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static hr.fer.rsikspr.paymentservice.common.Constants.USER_AUTHENTICATION_HEADER;

@RestController
@RequiredArgsConstructor
@Slf4j(topic = "ReceiptController")
public class ReceiptController {

  private final ReceiptService receiptService;
  private final BillingService billingService;

  @GetMapping("/{receiptId}")
  public ReceiptDAO getReceipt(@PathVariable("receiptId") int receiptId) {
    return receiptService.getReceipt(receiptId);
  }

  @PostMapping("/{billId}")
  public ReceiptDAO payBill(@PathVariable("billId") int billId,
                            @RequestHeader(USER_AUTHENTICATION_HEADER) String user,
                            @RequestBody PaymentDTO payment) {
    BillDTO bill = billingService.fetchAndValidateBill(billId, user);

    if (!bill.getState().equals(BillState.PENDING)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

    ReceiptDAO receipt = new ReceiptDAO();

    receipt.setBillId(bill.getId());
    receipt.setPrice(bill.getRate() * bill.getCount());
    receipt.setPaymentMethod(payment.getPaymentMethod());

    receipt = receiptService.saveReceipt(receipt);

    billingService.setBillPaid(billId, receipt.getId(), user);

    return receipt;
  }

}
