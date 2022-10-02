package koerber.pharma.blog.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import koerber.pharma.blog.model.entity.components.Post;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotEmpty
    private String name;

    @Column
    @Email
    private String email;

    @NotEmpty
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Post> userPosts;

}
