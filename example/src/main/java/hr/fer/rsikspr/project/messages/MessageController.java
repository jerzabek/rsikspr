package hr.fer.rsikspr.project.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class MessageController {

  @Autowired
  private MessageRepository messageRepository;

  @GetMapping(value = "/messages/today")
  public List<Message> getMessagesFromToday() {
    return messageRepository.findAllByDateCreated(LocalDate.now());
  }

  @PostMapping(value = "/messages")
  public Message createNewMessage(@RequestBody Message newMessage) {
    return messageRepository.save(newMessage);
  }

}
