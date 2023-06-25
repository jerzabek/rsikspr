package hr.fer.rsikspr.chatservice.authorization;

public class AuthorizationException extends RuntimeException {


  public AuthorizationException() {
    super("Unauthorized");
  }

  public AuthorizationException(String message) {
    super(message);
  }
}
