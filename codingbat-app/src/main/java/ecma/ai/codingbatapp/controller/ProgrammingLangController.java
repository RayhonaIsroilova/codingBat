package ecma.ai.codingbatapp.controller;

import ecma.ai.codingbatapp.entity.ProgrammingLanguage;
import ecma.ai.codingbatapp.entity.User;
import ecma.ai.codingbatapp.payload.ApiResponse;
import ecma.ai.codingbatapp.service.ProgrammingLangService;
import ecma.ai.codingbatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/ProgrammingLanguage")
public class ProgrammingLangController {
    @Autowired
    ProgrammingLangService service;

    @GetMapping
    public HttpEntity<?> getAll() {

        return ResponseEntity.ok(service.getPL());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getAll(@PathVariable Integer id) {

        return ResponseEntity.ok(service.getPLById(id));
    }


    @PostMapping
    public HttpEntity<?> register(@Valid @RequestBody ProgrammingLanguage programmingLanguage) {
        ApiResponse apiResponse = service.addPL(programmingLanguage);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@Valid @RequestBody ProgrammingLanguage language, @PathVariable Integer id) {
        ApiResponse apiResponse = service.updatePL(id, language);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> dell(@PathVariable Integer id) {
        ApiResponse apiResponse = service.deletePL(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
