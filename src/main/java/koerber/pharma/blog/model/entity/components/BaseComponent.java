package koerber.pharma.blog.model.entity.components;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Data
@MappedSuperclass
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
