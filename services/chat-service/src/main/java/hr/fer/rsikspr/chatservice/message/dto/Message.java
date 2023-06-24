package hr.fer.rsikspr.chatservice.message.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BasicMessage.class, name = "basic"),
        @JsonSubTypes.Type(value = ExtendedMessage.class, name = "extended")
})
public class Message {

  @Getter
  @Setter
  String from;


  @Getter
  @Setter
  String to;


}
