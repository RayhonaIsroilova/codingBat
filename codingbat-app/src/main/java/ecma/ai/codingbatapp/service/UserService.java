package ecma.ai.codingbatapp.service;

import ecma.ai.codingbatapp.entity.User;
import ecma.ai.codingbatapp.payload.ApiResponse;
import ecma.ai.codingbatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    /**
     * get all users
     *
     * @return user list
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * get user by id
     *
     * @param id Integer
     * @return user
     */
    public User getUsersById(Integer id) {
        Optional<User> byId = userRepository.findById(id);
        return byId.orElseGet(User::new);
    }

    /**
     * add new user
     *
     * @param userDTO user
     * @return ApiResponse
     */
    public ApiResponse addUser(User userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return new ApiResponse("this email is exist", false);
        }
        User user = new User(userDTO.getEmail(), userDTO.getPassword(), userDTO.getFullName());
        userRepository.save(user);
        return new ApiResponse("Success", true, user);
    }

    /**
     * update user by id
     *
     * @param id      int
     * @param userDto user
     * @return ApiResponse
     */
    public ApiResponse updateUser(Integer id, User userDto) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("id not found", false);
        }
        if (userRepository.existsByEmailAndIdNot(userDto.getEmail(), id)) {
            return new ApiResponse("email is already used", false);
        }
        User user = byId.get();

        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        user.setPassword(userDto.getPassword());

        userRepository.save(user);
        return new ApiResponse("Success", true, user);
    }

    /** delete user by id
     *
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse deleteUser(Integer id) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("id not found", false);
        }
        userRepository.deleteById(id);
         return new ApiResponse("success", true);
    }

}
