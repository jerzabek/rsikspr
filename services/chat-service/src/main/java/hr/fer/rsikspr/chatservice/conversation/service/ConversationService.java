package hr.fer.rsikspr.chatservice.conversation.service;

import hr.fer.rsikspr.chatservice.conversation.model.Conversation;
import hr.fer.rsikspr.chatservice.conversation.repository.ConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationService {

  private final ConversationRepository conversationRepository;

  public Conversation getConversation(int id) {
    return conversationRepository.findById(id).orElse(null);
  }

  public List<Conversation> getConversationsForUser(String user) {
    return conversationRepository.findAllByAuthor(user);
  }

  public boolean conversationExists(int id) {
    return conversationRepository.existsById(id);
  }

  public Conversation createConversation(String user, String title) {
    Conversation conversation = new Conversation();

    conversation.setAuthor(user);
    conversation.setTitle(title);

    return conversationRepository.save(conversation);
  }

  public void deleteConversation(int conversationId) {
    conversationRepository.deleteById(conversationId);
  }

}
