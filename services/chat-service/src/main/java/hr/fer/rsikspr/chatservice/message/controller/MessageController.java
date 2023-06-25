package hr.fer.rsikspr.chatservice.message.controller;

import hr.fer.rsikspr.chatservice.conversation.model.Conversation;
import hr.fer.rsikspr.chatservice.conversation.service.ConversationService;
import hr.fer.rsikspr.chatservice.message.dto.MessageDTO;
import hr.fer.rsikspr.chatservice.message.dto.MessageSummaryDTO;
import hr.fer.rsikspr.chatservice.message.model.MessageDAO;
import hr.fer.rsikspr.chatservice.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static hr.fer.rsikspr.chatservice.common.Constants.USER_AUTHENTICATION_HEADER;

@RestController
@Slf4j(topic = "MessageController")
@RequiredArgsConstructor
public class MessageController {

  private final MessageService messageService;
  private final ConversationService conversationService;

  @GetMapping("/conversations/{conversationId}/messages")
  public List<MessageSummaryDTO> getMessagesForConversation(@PathVariable("conversationId") int conversationId) {
    if (!conversationService.conversationExists(conversationId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return messageService.getMessagesInConversation(conversationId);
  }

  @PostMapping("/conversations/{conversationId}/messages")
  public void createMessageInConversation(@RequestHeader(USER_AUTHENTICATION_HEADER) String user,
                                          @PathVariable("conversationId") int conversationId,
                                          @RequestBody MessageDTO message) {
    if (!conversationService.conversationExists(conversationId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    MessageDAO parsedMessage = messageService.parseMessage(message);

    Conversation conversation = conversationService.getConversation(conversationId);
    parsedMessage.setConversation(conversation);
    parsedMessage.setAuthor(user);

    messageService.saveMessage(parsedMessage);
  }

}
