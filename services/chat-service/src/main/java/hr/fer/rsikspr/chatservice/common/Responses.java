package hr.fer.rsikspr.chatservice.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class Responses {

  public static ResponseEntity<Map<String, String>> notFound() {
    return new ResponseEntity<>(Map.of(), HttpStatus.NOT_FOUND);
  }

}
