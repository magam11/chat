package am.arssystems.chat.repository;

import am.arssystems.chat.model.Group;
import am.arssystems.chat.model.GroupUser;
import am.arssystems.chat.model.GroupUsersId;
import am.arssystems.chat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupUserRepository extends JpaRepository<GroupUser, GroupUsersId> {
    @Query(value = "select gu.group from GroupUser gu where gu.user.id=:userId")
    List<Group> getGroupsByUser(@Param("userId") int userId);
}
