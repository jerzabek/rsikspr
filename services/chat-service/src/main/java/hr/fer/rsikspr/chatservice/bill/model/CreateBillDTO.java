package hr.fer.rsikspr.chatservice.bill.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBillDTO {

  private int conversationId;
  private double rate;

}
