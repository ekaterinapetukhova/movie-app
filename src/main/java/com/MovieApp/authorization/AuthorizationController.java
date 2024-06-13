package com.MovieApp.authorization;

import com.MovieApp.user.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
@AllArgsConstructor
public class AuthorizationController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("registration")
    public String showRegistration(Model model) {
        model.addAttribute("user", new UserDto());
        model.addAttribute("module", "registration");

        return "registration";
    }

    private void authenticateUserAndSetSession(String username, String password, HttpServletRequest request) {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }
    }

    @PostMapping("registration")
    public String processRegistration(@Valid @ModelAttribute("user") UserDto userDto,
                                      BindingResult result,
                                      HttpServletRequest request
    ) {

        if (result.hasErrors()) return "registration";

        userService.saveUser(userDto);

        authenticateUserAndSetSession(userDto.getUsername(), userDto.getPassword(), request);

        return "redirect:/movies";
    }

    @GetMapping("login")
    public String showLoginPage(Model model) {
        model.addAttribute("module", "login");
        return "login";
    };
}
