package se.jensen.linus.awsstoreproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.jensen.linus.awsstoreproject.DTO.RegisterDTO;
import se.jensen.linus.awsstoreproject.service.UserService;

@Controller
@RequiredArgsConstructor
public class AuthenController {

    private final UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO("", "", ""));
        return "register";

    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegisterDTO registerDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        userService.registerUser(registerDTO);
        return "redirect:/login";
    }

    @GetMapping("login")
    public String showLoginForm() {
        return "login";

    }
}
