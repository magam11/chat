package am.arssystems.chat.repository;

import am.arssystems.chat.dto.responseWrapper.UserData;
import am.arssystems.chat.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select u from User u where u.email=:email")
    User getByEmail(@Param("email") String email);
    @Query(value = "select u from User u where u.id=:id")
    User findById(@Param("id")int id);

    @Query(value = "select u from User u where u.email=lower(:email)")
    User getUserByEmail(@Param("email")String email);

    @Query(value = "select new am.arssystems.chat.dto.responseWrapper.UserData(u.id, u.firstName,u.lastName,u.email,false) from User u" +
            " where u.id not in :currentUserId and (lower(u.firstName) like concat('%',lower(:text),'%') or lower(u.email) like concat('%',lower(:text),'%'))")
    Page<UserData> findAllByFirstNameOrEmailContainingAndIdNotIn(@Param("text")String text,
                                                                 @Param("currentUserId")Collection<Integer>  id,
                                                                 Pageable pageable);


}
