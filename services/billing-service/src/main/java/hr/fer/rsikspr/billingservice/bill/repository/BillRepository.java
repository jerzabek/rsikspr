package hr.fer.rsikspr.billingservice.bill.repository;

import hr.fer.rsikspr.billingservice.bill.model.BillDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<BillDAO, Integer> {

  List<BillDAO> findAllByAuthor(String author);

  BillDAO findByConversationId(int conversationId);

}
