package vn.edu.leading.shop.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import vn.edu.leading.shop.models.UserModel;
import vn.edu.leading.shop.repositories.RoleRepository;
import vn.edu.leading.shop.services.MailService;
import vn.edu.leading.shop.services.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final MailService mailService;

    public UserController(UserService userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder, MailService mailService) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    @PostMapping("/register")
    public String register(@Valid UserModel userModel) throws Exception {
        userService.register(userModel);
        mailService.sendMail(userModel);
        return "redirect:/login";
    }
}
