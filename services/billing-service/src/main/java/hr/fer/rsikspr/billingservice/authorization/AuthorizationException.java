package hr.fer.rsikspr.billingservice.authorization;

public class AuthorizationException extends RuntimeException {


  public AuthorizationException() {
    super("Unauthorized");
  }

  public AuthorizationException(String message) {
    super(message);
  }
}
