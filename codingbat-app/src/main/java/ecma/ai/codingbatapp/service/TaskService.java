package ecma.ai.codingbatapp.service;

import ecma.ai.codingbatapp.entity.Category;
import ecma.ai.codingbatapp.entity.Task;
import ecma.ai.codingbatapp.payload.ApiResponse;
import ecma.ai.codingbatapp.repository.CategoryRepository;
import ecma.ai.codingbatapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository repository;

    public List<Task> getAll() {
        return repository.findAll();
    }

    public Task getById(Integer id) {
        Optional<Task> byId = repository.findById(id);
        return byId.orElseGet(Task::new);
    }

    public ApiResponse add(Task task) {
        if (repository.existsByCategory_IdAndName(task.getCategory().getId(),task.getName())) {
            return new ApiResponse("this TASK is exist", false);
        }

        Task task1 = new Task(task.getName(), task.getDescription(), task.isCompleted(), task.getCategory(), task.getUser());
        repository.save(task1);
        return new ApiResponse("Success", true, task1);
    }

    public ApiResponse update(Integer id, Task task) {
        Optional<Task> byId = repository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("id not found", false);
        }
        if (repository.existsByCategory_IdAndNameAndIdNot(task.getCategory().getId(),task.getName(),id)) {
            return new ApiResponse("this TASK is exist", false);
        }
        Task task1 = byId.get();
        task1.setName(task.getName());
        task1.setDescription(task.getDescription());
        task1.getCategory().setId(task.getCategory().getId());
        task1.getUser().setId(task.getUser().getId());
        task1.setCompleted(false); //default
        return new ApiResponse("Success", true, task1);
    }

    public ApiResponse delete(Integer id) {
        Optional<Task> byId = repository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("id not found", false);
        }
        repository.deleteById(id);
        return new ApiResponse("success", true);
    }

}
