package hr.fer.rsikspr.project.messages;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity(name = "messages")
@Data
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column
  private String content;

  @Column
  private String authorName;

  @Column
  @CreationTimestamp
  private Timestamp dateCreated;
}
