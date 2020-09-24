package repo;

import model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MessageRepo extends JpaRepository<Message, UUID> {

	@Query(value = "select a.* from message a where a.topic_id = :topic_id", nativeQuery = true)
	public List<Message> getMessageByTopicId(@Param("topic_id") UUID topic_id);
}
