package hr.fer.rsikspr.paymentservice.receipt.service;

import hr.fer.rsikspr.paymentservice.receipt.model.ReceiptDAO;
import hr.fer.rsikspr.paymentservice.receipt.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceiptService {

  private final ReceiptRepository receiptRepository;

  public ReceiptDAO getReceipt(int receiptId) {
    return receiptRepository.findById(receiptId);
  }

  public ReceiptDAO saveReceipt(ReceiptDAO receipt) {
    return receiptRepository.save(receipt);
  }
}
