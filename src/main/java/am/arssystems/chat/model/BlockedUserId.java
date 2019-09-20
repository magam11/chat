package am.arssystems.chat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockedUserId implements Serializable {
    @Column(name = "user_id")
    private int userId;
    @Column(name = "blocked_user_id")
    private int blockedUserId;
}
