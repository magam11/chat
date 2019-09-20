package am.arssystems.chat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class GroupUsersId implements Serializable {

    @Column(name = "user_id")
    private int userId;

    @Column(name = "group_id")
    private int groupId;
}



