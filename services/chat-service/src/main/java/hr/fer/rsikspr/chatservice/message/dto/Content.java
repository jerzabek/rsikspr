package hr.fer.rsikspr.chatservice.message.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class Content {

  @Getter
  @Setter
  @NotBlank(message = "Text must not be blank")
  @NotNull(message = "Text must exist")
  String text;


  @Getter
  @Setter
  String imageURL;

}
