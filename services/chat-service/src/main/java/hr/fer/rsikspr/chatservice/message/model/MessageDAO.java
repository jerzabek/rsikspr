package hr.fer.rsikspr.chatservice.message.model;

import hr.fer.rsikspr.chatservice.conversation.model.Conversation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class MessageDAO {

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

  @ManyToOne
  @JoinColumn(nullable = false)
  private Conversation conversation;

}
