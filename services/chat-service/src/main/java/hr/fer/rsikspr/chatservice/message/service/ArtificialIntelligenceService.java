package hr.fer.rsikspr.chatservice.message.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ArtificialIntelligenceService {

  public String getResponse(String message) {
    if (message.contains("hello")) {
      return "Hello!";
    }

    if (message.endsWith("?")) {
      return getResponseToQuestion(message);
    }

    return "I don't know what to say.";
  }

  private String getResponseToQuestion(String message) {
    Random random = new Random();

    int randomInt = random.nextInt(6);

    switch (randomInt) {
      case 1 -> {
        return "Yes. Definitely.";
      }
      case 2 -> {
        return "No. Definitely not.";
      }
      case 3 -> {
        return "Maybe.";
      }
      case 4 -> {
        return "The odds are not in your favour.";
      }
      case 5 -> {
        return "The odds are so in your favour, my answer is yes.";
      }
      case 6 -> {
        return "It is what it is, you gotta go find that out for yourself.";
      }
      default -> {
        return "I don't know.";
      }
    }
  }

}
