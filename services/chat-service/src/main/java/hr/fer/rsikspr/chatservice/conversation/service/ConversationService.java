package hr.fer.rsikspr.chatservice.conversation.service;

import hr.fer.rsikspr.chatservice.bill.service.BillingService;
import hr.fer.rsikspr.chatservice.conversation.model.Conversation;
import hr.fer.rsikspr.chatservice.conversation.repository.ConversationRepository;
import hr.fer.rsikspr.chatservice.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "ConversationService")
public class ConversationService {

  private final ConversationRepository conversationRepository;
  private final MessageService messageService;
  private final BillingService billingService;

  private static final double RATE = 5;

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
    conversationRepository.save(conversation);

    billingService.createBill(user, conversation.getId(), RATE);

    return conversation;
  }

  public void endConversation(int conversationId) {
    Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND));

    conversation.setEndedAt(new Timestamp(System.currentTimeMillis()));
    conversationRepository.save(conversation);

    billingService.setBillPending(conversation.getAuthor(),
            conversation.getId(),
            messageService.getMessageCount(conversationId));
  }

}
