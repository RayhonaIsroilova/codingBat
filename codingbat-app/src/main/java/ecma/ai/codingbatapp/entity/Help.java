package ecma.ai.codingbatapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.security.DenyAll;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Help {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "bo'sh bo'lmasin")
    @NotEmpty(message = "Empty bo'lmasin")
    private String text;

    @NotNull(message = "bo'sh bo'lmasin")
    @NotEmpty(message = "Empty bo'lmasin")
    private String urlLink;

    public Help(String text, String urlLink) {
        this.text = text;
        this.urlLink = urlLink;
    }
}
