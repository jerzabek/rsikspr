package hr.fer.rsikspr.chatservice.conversation.dto;

import hr.fer.rsikspr.chatservice.conversation.model.Conversation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversationResponseDTO extends Conversation {

  public ConversationResponseDTO(Conversation conversation, int count) {
    super(conversation.getId(),
            conversation.getAuthor(),
            conversation.getTitle(),
            conversation.getCreatedAt(),
            conversation.getEndedAt());
    this.count = count;
  }

  private int count;

}
