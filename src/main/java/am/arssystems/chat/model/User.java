package am.arssystems.chat.model;

import am.arssystems.chat.model.view.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @JsonView(Views.Id.class)
    private int id;

    @Column
    @JsonView(Views.Base.class)
    private String email;

    @Column
    @JsonView(Views.Password.class)
    private String password;

    @Column
    @JsonView(Views.Base.class)
    private String firstName;

    @Column
    @JsonView(Views.Base.class)
    private String lastName;

    @Column
    private String token;

    @Column
    private Timestamp createdAt;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "blocked_users",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "blocked_user_id"))
//    private List<User> blockedUsers;

//    @Column(name = "preferences_themes")
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "group_users",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "group_id"))
//    private Set<Group> groups;

    @JsonIgnore
    @OneToMany(mappedBy = "blockedUser", orphanRemoval = true)
    private Set<BlockedUser> blockedUsers = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "group", orphanRemoval = true)
    private Set<GroupUser> groups = new HashSet<>();

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }

}
