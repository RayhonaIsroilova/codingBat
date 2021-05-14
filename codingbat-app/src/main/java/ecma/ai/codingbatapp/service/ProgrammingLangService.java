package ecma.ai.codingbatapp.service;

import ecma.ai.codingbatapp.entity.ProgrammingLanguage;
import ecma.ai.codingbatapp.entity.User;
import ecma.ai.codingbatapp.payload.ApiResponse;
import ecma.ai.codingbatapp.repository.PLRepository;
import ecma.ai.codingbatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammingLangService {
    @Autowired
    PLRepository plRepository;

    public List<ProgrammingLanguage> getPL() {
        return plRepository.findAll();
    }

    public ProgrammingLanguage getPLById(Integer id) {
        Optional<ProgrammingLanguage> byId = plRepository.findById(id);
        return byId.orElseGet(ProgrammingLanguage::new);
    }

    public ApiResponse addPL(ProgrammingLanguage pr) {
        if (plRepository.existsByName(pr.getName())) {
            return new ApiResponse("this NAME is exist", false);
        }
        ProgrammingLanguage language = new ProgrammingLanguage();
        language.setName(pr.getName());
        plRepository.save(language);
        return new ApiResponse("Success", true, language);
    }

    public ApiResponse updatePL(Integer id, ProgrammingLanguage prDto) {
        Optional<ProgrammingLanguage> byId = plRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("id not found", false);
        }
        if (plRepository.existsByNameAndIdNot(prDto.getName(),id)) {
            return new ApiResponse("this NAME is exist", false);
        }
        ProgrammingLanguage language = byId.get();
        language.setName(prDto.getName());
        plRepository.save(language);
        return new ApiResponse("Success", true, language);
    }

    public ApiResponse deletePL(Integer id) {
        Optional<ProgrammingLanguage> byId = plRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("id not found", false);
        }
        plRepository.deleteById(id);
         return new ApiResponse("success", true);
    }

}
