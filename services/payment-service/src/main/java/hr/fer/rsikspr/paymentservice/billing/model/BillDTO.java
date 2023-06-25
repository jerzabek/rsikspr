package hr.fer.rsikspr.paymentservice.billing.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class BillDTO {

  private int id;
  private Timestamp timestamp;
  private String conversationId;
  private String author;
  private double rate;
  private double count;
  private BillState state;

}
