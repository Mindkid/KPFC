package koerber.pharma.blog.model.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
