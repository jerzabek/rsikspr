package hr.fer.rsikspr.chatservice.message.service;

import hr.fer.rsikspr.chatservice.message.dto.BasicMessage;
import hr.fer.rsikspr.chatservice.message.dto.ExtendedMessage;
import hr.fer.rsikspr.chatservice.message.dto.MessageDTO;
import hr.fer.rsikspr.chatservice.message.dto.MessageSummaryDTO;
import hr.fer.rsikspr.chatservice.message.model.MessageDAO;
import hr.fer.rsikspr.chatservice.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

  private final MessageRepository messageRepository;

  public List<MessageSummaryDTO> getMessagesInConversation(int conversationId) {
    return messageRepository.findAllByConversationId(conversationId);
  }

  public MessageDAO parseMessage(MessageDTO message) {
    MessageDAO parsedMessage = new MessageDAO();

    try {
      switch (message) {
        case BasicMessage basicMessage -> {
          parsedMessage.setText(basicMessage.getText());
        }

        case ExtendedMessage extendedMessage -> {
          parsedMessage.setText(extendedMessage.getContent().getText());
          parsedMessage.setImageURL(extendedMessage.getContent().getImageURL());
        }

        default -> {
          throw new RuntimeException();
        }
      }
    } catch (Exception e) {
      throw new RuntimeException("Invalid message format");
    }

    return parsedMessage;
  }

  public void saveMessage(MessageDAO message) {
    messageRepository.save(message);
  }

}
