package hr.fer.rsikspr.chatservice.conversation.repository;

import hr.fer.rsikspr.chatservice.conversation.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Integer> {

  List<Conversation> findAllByAuthor(String author);

  boolean existsById(int id);

  void deleteById(int id);

}
