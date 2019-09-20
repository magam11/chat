package am.arssystems.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "blocked_users")
public class BlockedUser {

    @JsonIgnore
    @EmbeddedId
    private BlockedUserId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("blockedUserId")
    private User blockedUser;
    @Column(name = "blocked_at")
    private Timestamp blockedAt;

    @PrePersist
    public void prePersist() {
        blockedAt = Timestamp.valueOf(LocalDateTime.now());
    }


}
