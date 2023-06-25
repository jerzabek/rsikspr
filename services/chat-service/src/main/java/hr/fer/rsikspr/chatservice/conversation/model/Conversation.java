package hr.fer.rsikspr.chatservice.conversation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Conversation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column
  private String author;

  @Column
  private String title;

  @CreationTimestamp
  private Timestamp createdAt;

  @Column(nullable = true)
  private Timestamp endedAt;

}
