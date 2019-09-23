package am.arssystems.chat.model;

import am.arssystems.chat.model.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
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
@Table(name = "groups")
public class Group {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private int id;

    @Column
    @JsonView(Views.Base.class)
    private String name;

    @Column
    @JsonView(Views.Base.class)
    private String imagePath;

    @Column(name = "created_at")
    @JsonView(Views.Base.class)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @JsonView(Views.Special.class)
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @JsonView(Views.Special.class)
    private User owner;

    @PreUpdate
    public void preUpdate(){
        updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }

    @PrePersist
    public void prePersist(){
        createdAt =Timestamp.valueOf(LocalDateTime.now());
    }



}
