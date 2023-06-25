package hr.fer.rsikspr.billingservice.bill.service;

import hr.fer.rsikspr.billingservice.bill.model.BillDAO;
import hr.fer.rsikspr.billingservice.bill.model.BillState;
import hr.fer.rsikspr.billingservice.bill.model.ConversationDTO;
import hr.fer.rsikspr.billingservice.bill.repository.BillRepository;
import hr.fer.rsikspr.billingservice.conversation.ConversationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "BillService")
public class BillService {

  private final BillRepository billRepository;
  private final ConversationService conversationService;

  public List<BillDAO> getBills(String user) {
    return billRepository.findAllByAuthor(user);
  }

  public BillDAO createBill(int conversationId, double rate, String user) {
    ConversationDTO conversation = conversationService.fetchAndValidateConversation(conversationId, user);

    BillDAO bill = new BillDAO();

    bill.setConversationId(conversation.getId());
    bill.setRate(rate);
    bill.setAuthor(conversation.getAuthor());
    bill.setState(BillState.OPEN);

    return billRepository.save(bill);
  }

  public void setBillPending(int billId) {
    BillDAO bill = billRepository.findById(billId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    bill.setState(BillState.PENDING);

    billRepository.save(bill);
  }

  public void setBillPaid(int billId, int receiptId) {
    BillDAO bill = billRepository.findById(billId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    if (bill.getState() != BillState.PENDING) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    

    bill.setState(BillState.PAID);

    billRepository.save(bill);
  }

}
