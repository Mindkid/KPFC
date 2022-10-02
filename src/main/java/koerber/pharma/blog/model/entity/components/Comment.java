package koerber.pharma.blog.model.entity.components;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity(name = "comment")
public class Comment extends BaseComponent{
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;
}
