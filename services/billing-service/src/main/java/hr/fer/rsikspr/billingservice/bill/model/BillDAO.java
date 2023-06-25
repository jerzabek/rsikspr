package hr.fer.rsikspr.billingservice.bill.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class BillDAO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @CreationTimestamp
  private Timestamp createdAt;

  @Column
  private int conversationId;

  @Column
  private String author;

  @Column(nullable = true)
  private int receiptId;

  @Column
  private double rate;

  @Column
  private BillState state;

}
