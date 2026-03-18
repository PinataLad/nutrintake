package com.example.demo;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.HashMap;
import java.util.Map;

//Handles basic authentication routes like login, registration, and logout.
//ISSUE: This implementation uses in-memory storage for development purposes. Users will be lost when the application restarts.
@Controller
public class AuthController {

    //Temporary in-memory user store (email and password).
    private final Map<String, String> users = new HashMap<>();

    //displays the login page (GET request)
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    //Processes login form submission (POST request).
    @PostMapping("/login")
    public String doLogin(@RequestParam String email, @RequestParam String password, HttpSession session, RedirectAttributes ra) {

        // Look up stored password for this email (null if user doesn't exist)
        String savedPassword = users.get(email);

        // if credentials are valid then the user is redirected to the dashboard
        if (savedPassword != null && savedPassword.equals(password)) {
            session.setAttribute("userEmail", email);
            return "redirect:/dashboard";
        }

        //if the credentials are invalid the user is shown a message and redirected to login
        ra.addFlashAttribute("msg", "Invalid email or password");
        return "redirect:/login";
    }

    //Displays the sign-up page (GET request).
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    //Processes registration form submission (POST request).
    @PostMapping("/register")
    public String doRegister(@RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword, HttpSession session, RedirectAttributes ra) {

        // Prevent duplicate accounts
        if (users.containsKey(email)) {
            ra.addFlashAttribute("msg", "That email is already registered.");
            return "redirect:/register";
        }

        // checks that both the password fields match
        if (!password.equals(confirmPassword)) {
            ra.addFlashAttribute("msg", "Passwords do not match.");
            return "redirect:/register";
        }

        // Save the user in memory
        users.put(email, password);
        session.setAttribute("userEmail", email); // auto-login after signup
        return "redirect:/dashboard";
    }

    //Logs the user out by invalidating the session.
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}