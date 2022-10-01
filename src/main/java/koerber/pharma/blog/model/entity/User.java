package koerber.pharma.blog.model.entity;

import koerber.pharma.blog.model.entity.components.Post;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
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
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Post> userPosts;

}
