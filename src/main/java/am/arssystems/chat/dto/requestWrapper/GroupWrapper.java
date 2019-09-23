package am.arssystems.chat.dto.requestWrapper;

import am.arssystems.chat.model.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GroupWrapper {
    @JsonView(Views.Id.class)
    private int id;
    @JsonView(Views.Base.class)
    @NotBlank
    private String groupName;
}
