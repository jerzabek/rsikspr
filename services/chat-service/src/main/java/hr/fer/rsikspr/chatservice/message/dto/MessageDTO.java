package hr.fer.rsikspr.chatservice.message.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "format")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BasicMessage.class, name = "basic"),
        @JsonSubTypes.Type(value = ExtendedMessage.class, name = "extended")
})
public class MessageDTO {

  @Getter
  @Setter
  @NotNull(message = "From field must exist")
  @NotBlank(message = "From field must not be blank")
  String from;


  @Getter
  @Setter
//  @NotNull(message = "To field must exist")
//  @NotBlank(message = "To field must not be blank")
  String to;


}
