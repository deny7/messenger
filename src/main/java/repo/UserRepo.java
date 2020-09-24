package repo;

import model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<Users, String> {

	@Query(value = "select u.* from users u where u.user_name = :user_name", nativeQuery = true)
	List<Users> findByUserName(@Param("user_name") String user_name);
}
