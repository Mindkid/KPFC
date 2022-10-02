package koerber.pharma.blog.model.entity.components;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;



@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class BaseComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotEmpty
    private String title;

    @Column
    @NotEmpty
    private String body;
}
