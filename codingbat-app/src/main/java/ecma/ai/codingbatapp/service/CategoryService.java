package ecma.ai.codingbatapp.service;

import ecma.ai.codingbatapp.entity.Category;
import ecma.ai.codingbatapp.entity.ProgrammingLanguage;
import ecma.ai.codingbatapp.payload.ApiResponse;
import ecma.ai.codingbatapp.repository.CategoryRepository;
import ecma.ai.codingbatapp.repository.PLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository repository;

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Category getById(Integer id) {
        Optional<Category> byId = repository.findById(id);
        return byId.orElseGet(Category::new);
    }

    public ApiResponse add(Category category) {
        if (repository.existsByName(category.getName())) {
            return new ApiResponse("this NAME is exist", false);
        }
        Category category1 = new Category(category.getName(), category.getDescription(), category.getLanguageList());
        repository.save(category1);
        return new ApiResponse("Success", true, category1);
    }

    public ApiResponse update(Integer id, Category categoryDto) {
        Optional<Category> byId = repository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("id not found", false);
        }
        if (repository.existsByNameAndIdNot(categoryDto.getName(),id)) {
            return new ApiResponse("this NAME is exist", false);
        }
        Category category = byId.get();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setLanguageList(categoryDto.getLanguageList());
        repository.save(category);
        return new ApiResponse("Success", true, category);
    }

    public ApiResponse delete(Integer id) {
        Optional<Category> byId = repository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("id not found", false);
        }
        repository.deleteById(id);
        return new ApiResponse("success", true);
    }

}
