package hr.fer.rsikspr.chatservice.message.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

@JsonTypeName("basic")
public class BasicMessage extends Message {

  @Getter
  @Setter
  String text;

}
