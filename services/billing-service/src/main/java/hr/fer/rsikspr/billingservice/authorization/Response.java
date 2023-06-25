package hr.fer.rsikspr.billingservice.authorization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Response {

  public static Map<String, String> getErrorBody(String message) {
    return Map.of("error", message);
  }

  public static String getSerializedErrorBody(String message) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();

    return objectMapper.writeValueAsString(getErrorBody(message));
  }

}
