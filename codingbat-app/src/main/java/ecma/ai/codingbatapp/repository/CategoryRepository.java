package ecma.ai.codingbatapp.repository;

import ecma.ai.codingbatapp.entity.Category;
import ecma.ai.codingbatapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}
