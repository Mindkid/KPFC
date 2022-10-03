package koerber.pharma.blog.model.rest;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class PostRequest {
    @NotEmpty
    private String title;

    @NotEmpty
    private String body;

    @NotEmpty
    private String userEmail;
}
