package am.arssystems.chat.repository;

import am.arssystems.chat.model.BlockedUser;
import am.arssystems.chat.model.BlockedUserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockedUserRepository extends JpaRepository<BlockedUser, BlockedUserId> {
}
