package am.arssystems.chat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "group_users")
public class GroupUser {
    @EmbeddedId
    private GroupUsersId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("groupId")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @Column(name = "added_at")
    private Timestamp addedAt;

    @PrePersist
    public void prePersist(){
        addedAt = Timestamp.valueOf(LocalDateTime.now());
    }

}
