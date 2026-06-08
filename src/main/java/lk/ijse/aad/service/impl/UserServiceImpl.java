package lk.ijse.aad.service.impl;

import lk.ijse.aad.dto.UserDTO;
import lk.ijse.aad.entity.User;
import lk.ijse.aad.enumaration.UserStatus;
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

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        log.info("Updating user with id: {}", userDTO.getId());
        try {
            Optional<User> userOptional = userRepository.findById(userDTO.getId());
            if (userOptional.isEmpty()) {
                throw new RuntimeException("User not found with id: " + userDTO.getId());
            }
            User user = userOptional.get();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            //user.setBirthDate(userDTO.getBirthDate());
            user.setStatus(userDTO.getStatus());
            User userUp = userRepository.save(user);
            log.info("User updated successfully with id: {}", userDTO.getId());
            return new UserDTO(userUp.getId(), userUp.getFirstName(), userUp.getLastName(), userUp.getBirthDate(), userUp.getStatus());
        } catch (Exception e) {
            log.error("Error updating user with id: {} : {}", userDTO.getId(), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUserStatus(UserDTO userDTO) {
            log.info("Updating user status with id: {}", userDTO.getId());
            try {
                Optional<User> userOptional = userRepository.findById(userDTO.getId());
                if (userOptional.isEmpty()) {
                    throw new RuntimeException("User not found with id: " + userDTO.getId());
                }
                if (userDTO.getStatus() == null) {
                    throw new RuntimeException("User status cannot be null ");
                }
                User user = userOptional.get();
                user.setStatus(userDTO.getStatus());
                userRepository.save(user);
                log.info("User status updated successfully with id: {}", userDTO.getId());
            } catch (Exception e) {
                log.error("Error updating user status with id: {} : {}", userDTO.getId(), e.getMessage());
                throw new RuntimeException(e);
            }
    }

    @Override
    public void deleteUser(Long userId) {
        log.info("Deleting user with id: {}", userId);
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) {
                throw new RuntimeException("User not found with id: " + userId);
            }
            User  user = userOptional.get();
            user.setStatus(UserStatus.DELETED);
            userRepository.save(user);
            log.info("User deleted successfully with id: {}", userId);
        } catch (Exception e) {
            log.error("Error deleting user with id: {} : {}", userId, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserDTO> searchUsersByName(String firstName, String lastName) {
       try {
          List<User> users = userRepository.findByFirstNameAndLastName(firstName, lastName);

          List<UserDTO> userDTOS = new ArrayList<>();
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
       }catch (Exception e) {
           log.error("Error searching users by name: {}", e.getMessage());
           throw new RuntimeException(e);
       }
    }


}