package hr.fer.rsikspr.paymentservice.errorboundary;


import hr.fer.rsikspr.paymentservice.authorization.AuthorizationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

import static hr.fer.rsikspr.paymentservice.common.Constants.USER_AUTHENTICATION_HEADER;

@RestControllerAdvice
public class AuthorizationExceptionHandler {

  @ExceptionHandler(AuthorizationException.class)
  public ResponseEntity<Map<String, String>> handleAuthorizationException(AuthorizationException ex) {
    Map<String, String> errorBody = Map.of("error", ex.getMessage());

    return new ResponseEntity<>(errorBody, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(MissingRequestHeaderException.class)
  public ResponseEntity<Map<String, String>> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
    if (ex.getMessage().contains(USER_AUTHENTICATION_HEADER)) {
      return handleAuthorizationException(new AuthorizationException());
    }

    Map<String, String> errorBody = Map.of("error", ex.getMessage());

    return new ResponseEntity<>(errorBody, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }
}
