package lk.ijse.aad.service.impl;

import lk.ijse.aad.dto.UserDTO;
import lk.ijse.aad.entity.User;
import lk.ijse.aad.repository.UserRepository;
import lk.ijse.aad.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        log.info("Saving user");
//        User user = new User();
//        user.setFirstName("Sachin");
//        user.setLastName("Sachin");
//        user.setBirthDate(LocalDate.parse("1997-11-11"));
//        user.setStatus(UserStatus.ACTIVE);
//        userRepository.save(user);

        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setBirthDate(userDTO.getBirthDate());
        user.setStatus(userDTO.getStatus());
        userRepository.save(user);
        log.info("User saved successfully");
        log.info("User saved Returned");

        return userDTO;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        log.info("Getting all users");
        try {
            List<UserDTO> userDTOS = new ArrayList<>();
            List<User> users = userRepository.findAll();
            for (User user : users) {
                UserDTO userDTO = new UserDTO();
                userDTO.setId(user.getId());
                userDTO.setFirstName(user.getFirstName());
                userDTO.setLastName(user.getLastName());
                userDTO.setBirthDate(user.getBirthDate());
                userDTO.setStatus(user.getStatus());
                userDTOS.add(userDTO);
            }
            return userDTOS;
        } catch (Exception e) {
            log.error("Error getting all users :{}", e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public UserDTO getUserById(long id) {
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isEmpty()) {
                throw new RuntimeException("User not found with id: " + id);
            }
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.get().getId());
            userDTO.setFirstName(user.get().getFirstName());
            userDTO.setLastName(user.get().getLastName());
            userDTO.setBirthDate(user.get().getBirthDate());
            userDTO.setStatus(user.get().getStatus());
            return userDTO;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
