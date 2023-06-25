package hr.fer.rsikspr.paymentservice.receipt.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class ReceiptDAO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @CreationTimestamp
  private Timestamp timestamp;

  @Column
  private double price;

  @Column
  private String paymentMethod;

  @Column
  private int billId;

}
