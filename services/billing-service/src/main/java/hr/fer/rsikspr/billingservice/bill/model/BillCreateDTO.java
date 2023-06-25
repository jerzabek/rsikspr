package hr.fer.rsikspr.billingservice.bill.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillCreateDTO {

  private int conversationId;
  private double rate;
}
