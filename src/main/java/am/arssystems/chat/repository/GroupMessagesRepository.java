package am.arssystems.chat.repository;

import am.arssystems.chat.model.GroupMessages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMessagesRepository extends JpaRepository<GroupMessages, Integer> {


}
