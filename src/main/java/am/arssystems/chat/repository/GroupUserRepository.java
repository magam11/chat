package am.arssystems.chat.repository;

import am.arssystems.chat.model.GroupUser;
import am.arssystems.chat.model.GroupUsersId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupUserRepository extends JpaRepository<GroupUser, GroupUsersId> {
}
