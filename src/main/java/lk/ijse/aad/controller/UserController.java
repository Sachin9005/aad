package lk.ijse.aad.controller;

import lk.ijse.aad.dto.UserDTO;
import lk.ijse.aad.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE) //JSON response turns to JSON String
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO searchUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateUserStatus(@RequestBody UserDTO userDTO) {
        userService.updateUserStatus(userDTO);
        return "User status updated successfully";
    }

    @DeleteMapping(value = "/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteUser(@PathVariable String userid) {
        userService.deleteUser(Long.parseLong(userid));
        return "User deleted successfully";
    }

    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> searchUsersByName(@RequestParam (value = "firstName", required = false) String firstName,
                                           @RequestParam (value = "lastName", required = false) String lastName) {
        return userService.searchUsersByName(firstName, lastName).isEmpty() ? null : userService.searchUsersByName(firstName, lastName);
    }
}
