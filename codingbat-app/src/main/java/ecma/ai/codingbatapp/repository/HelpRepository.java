package ecma.ai.codingbatapp.repository;

import ecma.ai.codingbatapp.entity.Help;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Repository
public interface HelpRepository extends JpaRepository<Help,Integer> {

    boolean existsByUrlLink(@NotNull(message = "bo'sh bo'lmasin") @NotEmpty(message = "Empty bo'lmasin") String urlLink);
}
