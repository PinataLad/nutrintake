package com.example.demo;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {

    private final Map<String, String> users = new HashMap<>();

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          HttpSession session,
                          RedirectAttributes ra) {

        String savedPassword = users.get(email);

        if (savedPassword != null && savedPassword.equals(password)) {
            session.setAttribute("userEmail", email);
            return "redirect:/dashboard";
        }

        ra.addFlashAttribute("msg", "Invalid email or password");
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@RequestParam String email,
                             @RequestParam String password,
                             @RequestParam String confirmPassword,
                             HttpSession session,
                             RedirectAttributes ra) {

        if (users.containsKey(email)) {
            ra.addFlashAttribute("msg", "That email is already registered.");
            return "redirect:/register";
        }

        if (!password.equals(confirmPassword)) {
            ra.addFlashAttribute("msg", "Passwords do not match.");
            return "redirect:/register";
        }

        users.put(email, password);
        session.setAttribute("userEmail", email); // auto-login after signup
        return "redirect:/dashboard";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}