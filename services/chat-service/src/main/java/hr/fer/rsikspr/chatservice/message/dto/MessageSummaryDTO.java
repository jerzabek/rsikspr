package hr.fer.rsikspr.chatservice.message.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageSummaryDTO {

  private int id;
  private Timestamp createdAt;
  private String author;
  private String text;
  private String imageURL;
}
