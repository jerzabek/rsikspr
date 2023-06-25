package hr.fer.rsikspr.billingservice.bill.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConversationDTO {

  private int id;
  private String author;
  private String title;
  private Timestamp createdAt;
  private Timestamp endedAt;

}
