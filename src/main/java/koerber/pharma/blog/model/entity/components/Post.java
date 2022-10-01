package koerber.pharma.blog.model.entity.components;

import koerber.pharma.blog.model.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "posts")
public class Post extends BaseComponent{
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> postComments;

}
