package spring.intro.controllers;

import spring.intro.dto.UserResponseDto;
import spring.intro.model.User;
import spring.intro.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/inject")
    public String injectUser() {
        User user1 = new User();
        user1.setName("user1");
        user1.setPassword("pass1");

        User user2 = new User();
        user2.setName("user2");
        user2.setPassword("pass2");

        User user3 = new User();
        user3.setName("user3");
        user3.setPassword("pass3");

        User user4 = new User();
        user4.setName("user4");
        user4.setPassword("pass4");

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        return "Users was injected!";
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        User user = userService.get(id);
        return getDtoFromEntity(id, user);
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        userService.listUsers().forEach(u -> {
            userResponseDtoList.add(getDtoFromEntity(u.getId(), u));
        });
        return userResponseDtoList;
    }

    private UserResponseDto getDtoFromEntity(Long id, User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(id);
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPassword(user.getPassword());
        return userResponseDto;
    }
}


