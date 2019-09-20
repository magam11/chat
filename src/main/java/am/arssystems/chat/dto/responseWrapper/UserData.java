package am.arssystems.chat.dto.responseWrapper;


import am.arssystems.chat.model.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Builder
public class UserData {
    @JsonView(Views.Id.class)
    private int id;

    @JsonView(Views.Base.class)
    private String firstName;

    @JsonView(Views.Base.class)
    private String lastName;

    @JsonView(Views.Base.class)
    private String email;

    @JsonView(Views.Special.class)
    private boolean inGroup;


    public UserData(int id, String firstName, String lastName, String email, boolean inGroup) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.inGroup = inGroup;
    }

}
