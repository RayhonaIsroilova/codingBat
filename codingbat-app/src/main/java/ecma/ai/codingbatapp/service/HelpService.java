package ecma.ai.codingbatapp.service;

import ecma.ai.codingbatapp.entity.Help;
import ecma.ai.codingbatapp.entity.Task;
import ecma.ai.codingbatapp.payload.ApiResponse;
import ecma.ai.codingbatapp.repository.HelpRepository;
import ecma.ai.codingbatapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HelpService {
    @Autowired
    HelpRepository repository;

    public List<Help> getAll() {
        return repository.findAll();
    }

    public Help getById(Integer id) {
        Optional<Help> byId = repository.findById(id);
        return byId.orElseGet(Help::new);
    }

    public ApiResponse add(Help help) {
        if (repository.existsById(help.getId())) {
            return new ApiResponse("this id is exist", false);
        }

        Help help1 = new Help(help.getText(), help.getUrlLink());
        repository.save(help1);
        return new ApiResponse("Success", true, help1);
    }

    public ApiResponse update(Integer id, Help help) {
        Optional<Help> byId = repository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("id not found", false);
        }
        if (repository.existsByUrlLink(help.getUrlLink())) {
            return new ApiResponse("this urlLink is exist", false);
        }
        Help help1 = byId.get();
        help1.setText(help.getText());
        help1.setUrlLink(help.getUrlLink());
        repository.save(help1);
        return new ApiResponse("Success", true, help1);
    }

    public ApiResponse delete(Integer id) {
        Optional<Help> byId = repository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("id not found", false);
        }
        repository.deleteById(id);
        return new ApiResponse("success", true);
    }

}
