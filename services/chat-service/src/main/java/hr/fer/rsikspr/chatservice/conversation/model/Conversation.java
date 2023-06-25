package hr.fer.rsikspr.chatservice.conversation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
