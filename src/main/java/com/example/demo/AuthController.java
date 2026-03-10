package com.example.demo;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    // temporary demo login (replace with database later)
    private final String DEMO_EMAIL = "test@sdsu.edu";
    private final String DEMO_PASS  = "password";

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          HttpSession session,
                          RedirectAttributes ra) {

        if (DEMO_EMAIL.equals(email) && DEMO_PASS.equals(password)) {
            session.setAttribute("userEmail", email); // "logged in"
            return "redirect:/dashboard";
        }

        ra.addFlashAttribute("msg", "Invalid email or password");
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}