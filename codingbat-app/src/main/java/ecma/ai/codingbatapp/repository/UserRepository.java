package ecma.ai.codingbatapp.repository;

import ecma.ai.codingbatapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(@Email(message = "Email valid emas!")
                                  @NotNull(message = "bo'sh bo'lmasin")
                                  @NotEmpty(message = "Empty bo'lmasin")
                                          String email, Integer id);
}
