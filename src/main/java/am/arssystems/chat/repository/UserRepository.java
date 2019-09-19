package am.arssystems.chat.repository;

import am.arssystems.chat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select u from User u where u.email=:email")
    User getByEmail(@Param("email") String email);
    @Query(value = "select u from User u where u.id=:id")
    User findById(@Param("id")int id);
}
