package hr.fer.rsikspr.paymentservice.receipt.repository;

import hr.fer.rsikspr.paymentservice.receipt.model.ReceiptDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<ReceiptDAO, Integer> {

  ReceiptDAO findById(int receiptId);

}
