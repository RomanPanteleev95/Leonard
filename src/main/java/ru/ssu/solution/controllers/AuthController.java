package ru.ssu.solution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ssu.solution.entities.User;
import ru.ssu.solution.services.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/sign_in")
    public String login() {
        return "sign_in";
    }

    @PostMapping ("/sign_in")
    public String loginPost() {
        return "files";
    }

    @GetMapping("/sign_up")
    public String signUp() {
        return "sign_up";
    }

    @PostMapping("/sign_up")
    public String signUp(@ModelAttribute User user) {
        userService.save(user);
        System.out.println("save success");
        return "sign_in";
    }
}
