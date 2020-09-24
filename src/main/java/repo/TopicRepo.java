package repo;

import model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TopicRepo extends JpaRepository<Topic, UUID> {
	@Query(value = "select a.* from topic a where a.create_user_id = :user_id", nativeQuery = true)
	public List<Topic> getTopicsByUserId(@Param("user_id") String user_id);
}
