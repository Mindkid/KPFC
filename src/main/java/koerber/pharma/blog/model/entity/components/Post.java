package koerber.pharma.blog.model.entity.components;

import com.fasterxml.jackson.annotation.JsonIgnore;
import koerber.pharma.blog.model.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity(name = "posts")
public class Post extends BaseComponent{
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> postComments;

}
