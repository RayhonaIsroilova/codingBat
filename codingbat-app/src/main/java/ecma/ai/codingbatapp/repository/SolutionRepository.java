package ecma.ai.codingbatapp.repository;

import ecma.ai.codingbatapp.entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionRepository extends JpaRepository<Solution,Integer> {
}
