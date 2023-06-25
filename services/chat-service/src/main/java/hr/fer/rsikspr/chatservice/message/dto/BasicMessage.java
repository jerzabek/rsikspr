package hr.fer.rsikspr.chatservice.message.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@JsonTypeName("basic")
public class BasicMessage extends MessageDTO {

  @Getter
  @Setter
  @NotNull(message = "Content must exist")
  String text;

}
