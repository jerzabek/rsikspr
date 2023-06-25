package hr.fer.rsikspr.billingservice.bill.controller;

import hr.fer.rsikspr.billingservice.bill.model.BillCreateDTO;
import hr.fer.rsikspr.billingservice.bill.model.BillDAO;
import hr.fer.rsikspr.billingservice.bill.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hr.fer.rsikspr.billingservice.common.Constants.USER_AUTHENTICATION_HEADER;

@RestController
@RequiredArgsConstructor
public class BillController {

  private final BillService billService;

  @GetMapping("/")
  public List<BillDAO> getBills(@RequestHeader(USER_AUTHENTICATION_HEADER) String user) {
    return billService.getBills(user);
  }

  @PostMapping("/")
  public BillDAO createBill(@RequestHeader(USER_AUTHENTICATION_HEADER) String user, @RequestBody BillCreateDTO bill) {
    return billService.createBill(bill.getConversationId(), bill.getRate(), user);
  }

}
