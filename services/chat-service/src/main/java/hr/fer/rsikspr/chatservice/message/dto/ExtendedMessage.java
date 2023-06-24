package hr.fer.rsikspr.chatservice.message.dto;


import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@JsonTypeName("extended")
public class ExtendedMessage extends Message {

  @Getter
  @Setter
  @NotNull(message = "Content must exist")
  Content content;


  @Getter
  @Setter
  @NotBlank(message = "Display name must not be blank")
  @NotNull(message = "Display name must exist")
  String displayName;

}
