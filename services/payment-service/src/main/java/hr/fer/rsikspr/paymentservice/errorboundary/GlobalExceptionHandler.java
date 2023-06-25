package hr.fer.rsikspr.paymentservice.errorboundary;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException exception) {
    List<String> errors = collectFieldValidationErrors(exception);

    return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  private static List<String> collectFieldValidationErrors(MethodArgumentNotValidException exception) {
    return exception.getBindingResult()
            .getFieldErrors()
            .stream().map(FieldError::getDefaultMessage)
            .collect(Collectors.toList());
  }

  private static Map<String, List<String>> getErrorsMap(List<String> errors) {
    Map<String, List<String>> errorResponse = new HashMap<>();

    errorResponse.put("errors", errors);

    return errorResponse;
  }

}
