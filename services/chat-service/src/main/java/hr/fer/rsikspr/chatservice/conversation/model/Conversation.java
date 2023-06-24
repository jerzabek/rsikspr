package hr.fer.rsikspr.chatservice.conversation.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.sql.Timestamp;

@Entity
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
