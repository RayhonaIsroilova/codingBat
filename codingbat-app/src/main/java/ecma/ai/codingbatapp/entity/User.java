package ecma.ai.codingbatapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Email(message = "Email valid emas!")
    @Column(unique = true)
    @NotNull(message = "bo'sh bo'lmasin")
    @NotEmpty(message = "Empty bo'lmasin")
    private String email;

    @NotNull(message = "bo'sh bo'lmasin")
    private String password;
    @NotNull(message = "bo'sh bo'lmasin")
    private String fullName;

//    @OneToMany
//    private List<Task> taskList;


    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }
}
