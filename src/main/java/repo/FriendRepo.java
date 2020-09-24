package repo;

import model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendRepo extends JpaRepository<Friend, String> {

	@Query(value = "select f.* from friend f where user_id = :user_id", nativeQuery = true)
	List<Friend> findByUserId(@Param("user_id") String user_id);
}
