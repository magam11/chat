package am.arssystems.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthenticationRequest {
    @Email(message = "Invalid email!")
    @NotBlank(message = "Email can nott be null or empty ,min length = 4")
    private String email;
    @NotBlank(message = "Password can not be null or empty")
    @Size(min = 6,message = "Minimum length of password mast be 6")
    private String password;
}
