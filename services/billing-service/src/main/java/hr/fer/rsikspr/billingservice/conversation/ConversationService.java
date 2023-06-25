package hr.fer.rsikspr.billingservice.conversation;

import hr.fer.rsikspr.billingservice.bill.model.ConversationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import static hr.fer.rsikspr.billingservice.common.Constants.USER_AUTHENTICATION_HEADER;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "ConversationService")
public class ConversationService {

  private final RestTemplate restTemplate;

  @Value("${chat-service.api-url}")
  private String chatServiceApiUrl;

  public ConversationDTO fetchAndValidateConversation(int conversationId, String user) {
    String getConversationEndpoint = chatServiceApiUrl + "/conversations/" + conversationId;

    HttpHeaders headers = new HttpHeaders();

    headers.add(USER_AUTHENTICATION_HEADER, user);

    HttpEntity<String> entity = new HttpEntity<>(headers);
    ResponseEntity<ConversationDTO> responseEntity;

    try {
      responseEntity = restTemplate
              .exchange(getConversationEndpoint, HttpMethod.GET, entity, ConversationDTO.class);
    } catch (Exception e) {
      log.info("Failed to fetch conversation with id: {}", conversationId);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (responseEntity.getStatusCode() != HttpStatus.OK) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    ConversationDTO conversation = responseEntity.getBody();

    if (conversation == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (!conversation.getAuthor().equals(user)) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    return conversation;
  }

}
