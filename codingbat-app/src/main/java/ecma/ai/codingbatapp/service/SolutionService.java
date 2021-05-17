package ecma.ai.codingbatapp.service;

import ecma.ai.codingbatapp.entity.Category;
import ecma.ai.codingbatapp.entity.Solution;
import ecma.ai.codingbatapp.entity.Task;
import ecma.ai.codingbatapp.payload.ApiResponse;
import ecma.ai.codingbatapp.repository.CategoryRepository;
import ecma.ai.codingbatapp.repository.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolutionService {
    @Autowired
    SolutionRepository repository;

    public List<Solution> getAll() {
        return repository.findAll();
    }

    public Solution getById(Integer id) {
        Optional<Solution> byId = repository.findById(id);
        return byId.orElseGet(Solution::new);
    }

    public ApiResponse add(Solution solution) {
        if (repository.existsById(solution.getId())) {
            return new ApiResponse("this id is exist", false);
        }
        Solution solution1 = new Solution(solution.getText(), solution.getTask());
        repository.save(solution1);
        return new ApiResponse("Success", true, solution1);
    }

    public ApiResponse update(Integer id, Solution solution) {
        Optional<Solution> byId = repository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("id not found", false);
        }
        if (repository.existsById(id)) {
            return new ApiResponse("this id is exist", false);
        }
        Solution solution1 = byId.get();
        solution1.setTask(solution.getTask());
        solution1.setTask(solution.getTask());
        repository.save(solution1);
        return new ApiResponse("Success", true, solution1);
    }

    public ApiResponse delete(Integer id) {
        Optional<Solution> byId = repository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("id not found", false);
        }
        repository.deleteById(id);
        return new ApiResponse("success", true);
    }

}
