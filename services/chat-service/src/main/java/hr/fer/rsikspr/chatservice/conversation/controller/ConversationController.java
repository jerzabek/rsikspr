package hr.fer.rsikspr.chatservice.conversation.controller;

import hr.fer.rsikspr.chatservice.conversation.dto.ConversationCreationDTO;
import hr.fer.rsikspr.chatservice.conversation.model.Conversation;
import hr.fer.rsikspr.chatservice.conversation.service.ConversationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static hr.fer.rsikspr.chatservice.common.Constants.USER_AUTHENTICATION_HEADER;

@RestController
@RequiredArgsConstructor
@Slf4j(topic = "ConversationController")
public class ConversationController {

  private final ConversationService conversationService;

  @GetMapping("/conversations")
  public List<Conversation> getConversations(@RequestHeader(USER_AUTHENTICATION_HEADER) String user) {
    return conversationService.getConversationsForUser(user);
  }

  @PostMapping("/conversations")
  public Conversation createConversation(@RequestHeader(USER_AUTHENTICATION_HEADER) String user, @RequestBody ConversationCreationDTO conversationCreationDTO) {
    log.info("Hello");
    return conversationService.createConversation(user, conversationCreationDTO.getTitle());
  }

  @DeleteMapping("/conversations/{conversationId}")
  public void deleteConversation(@RequestHeader(USER_AUTHENTICATION_HEADER) String user, @PathVariable("conversationId") int conversationId) {
    if (!conversationService.conversationExists(conversationId)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    Conversation conversation = conversationService.getConversation(conversationId);

    if (!conversation.getAuthor().equals(user)) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    conversationService.deleteConversation(conversationId);
  }

}
