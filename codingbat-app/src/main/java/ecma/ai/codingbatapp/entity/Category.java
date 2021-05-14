package ecma.ai.codingbatapp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotNull(message = "bo'sh bo'lmasin")
    @NotEmpty(message = "Empty bo'lmasin")
    private String name;

    @NotNull(message = "bo'sh bo'lmasin")
    private String description;

    @ManyToMany
    @NotEmpty(message = "Empty bo'lmasin")
    private List<ProgrammingLanguage> languageList;

    public Category(String name, String description, List<ProgrammingLanguage> languageList) {
        this.name = name;
        this.description = description;
        this.languageList = languageList;
    }
}
