package ecma.ai.codingbatapp.repository;

import ecma.ai.codingbatapp.entity.ProgrammingLanguage;
import ecma.ai.codingbatapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//@Repository
public interface PLRepository extends JpaRepository<ProgrammingLanguage, Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(@NotNull(message = "bo'sh bo'lmasin")
                                 @NotEmpty(message = "Empty bo'lmasin") String name,
                                 Integer id);
}
