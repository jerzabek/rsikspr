package hr.fer.rsikspr.chatservice.message.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @CreationTimestamp
  private Timestamp createdAt;

  @Column
  private String author;

  @Column
  private String text;

  @Column(nullable = true)
  private String imageURL;

}
