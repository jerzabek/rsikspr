package hr.fer.rsikspr.project.messages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

  @Query("SELECT m FROM messages m WHERE DATE(m.dateCreated) = :today")
  List<Message> findAllByDateCreated(@Param("today") LocalDate today);


}
