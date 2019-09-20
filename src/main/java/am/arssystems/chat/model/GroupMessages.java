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
@Table(name = "group_messages")
public class GroupMessages {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int fileId;
    @ManyToOne
    private Group group;
    @ManyToOne
    private User fromUser;
    @Column
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }
}
