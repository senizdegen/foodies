package kz.senizdegen.foodiesapi.controller;

import kz.senizdegen.foodiesapi.io.UserRequest;
import kz.senizdegen.foodiesapi.io.UserResponse;
import kz.senizdegen.foodiesapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@RequestBody UserRequest request) {
        return userService.registerUser(request);
    }
}
