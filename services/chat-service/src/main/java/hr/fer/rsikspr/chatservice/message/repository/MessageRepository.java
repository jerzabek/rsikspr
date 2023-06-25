package hr.fer.rsikspr.chatservice.message.repository;

import hr.fer.rsikspr.chatservice.message.dto.MessageSummaryDTO;
import hr.fer.rsikspr.chatservice.message.model.MessageDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageDAO, Integer> {

  List<MessageSummaryDTO> findAllByConversationId(int conversationId);

}
