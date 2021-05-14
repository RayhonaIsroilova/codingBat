package ecma.ai.codingbatapp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "bo'sh bo'lmasin")
    @NotEmpty(message = "Empty bo'lmasin")
    private String name;

    @NotNull(message = "bo'sh bo'lmasin")
    private String description;

    @NotNull(message = "bo'sh bo'lmasin")
    private boolean completed = false;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    public Task(@NotNull(message = "bo'sh bo'lmasin")
                @NotEmpty(message = "Empty bo'lmasin") String name,
                @NotNull(message = "bo'sh bo'lmasin") String description,
                @NotNull(message = "bo'sh bo'lmasin") boolean completed,
                Category category, User user) {
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.category = category;
        this.user = user;
    }
}
