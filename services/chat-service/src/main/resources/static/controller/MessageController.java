package hr.fer.rsikspr.chatservice.message.controller;

import hr.fer.rsikspr.chatservice.message.dto.BasicMessage;
import hr.fer.rsikspr.chatservice.message.dto.ExtendedMessage;
import hr.fer.rsikspr.chatservice.message.dto.Message;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j(topic = "MessageController")
public class MessageController {

  @PostMapping("/message")
  public ResponseEntity<String> receiveMessage(@RequestBody @Valid Message message) {
    String type;

    try {
      switch (message) {
        case BasicMessage basicMessage -> {
          type = "Basic message";
          log.info("Received basic message: " + basicMessage.getText());
        }

        case ExtendedMessage extendedMessage -> {
          type = "Extended message";
          log.info("Received extended message: " + extendedMessage.getContent().getText());
        }

        default -> {
          log.info("Unknown ://");
          return ResponseEntity.badRequest().body("Unknown message type");
        }
      }
    } catch (Exception e) {
      log.error("Could not format request", e);
      return ResponseEntity.badRequest().body("Invalid format");
    }

    return ResponseEntity.ok().body(String.format("Message received (type: %s)", type));
  }

}
