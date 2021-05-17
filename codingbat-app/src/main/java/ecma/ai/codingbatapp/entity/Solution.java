package ecma.ai.codingbatapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "bo'sh bo'lmasin")
    @NotEmpty(message = "Empty bo'lmasin")
    private String text;


    @ManyToOne
    @NotEmpty(message = "Empty bo'lmasin")
    private Task task;

    public Solution(String text, Task task) {
        this.text = text;
        this.task = task;
    }
}
