package kz.kbtu.artifact.service.controllers;

import jakarta.validation.Valid;
import kz.kbtu.artifact.service.dto.AuthResponse;
import kz.kbtu.artifact.service.dto.LoginRequest;
import kz.kbtu.artifact.service.dto.RegisterRequest;
import kz.kbtu.artifact.service.entity.Role;
import kz.kbtu.artifact.service.entity.User;
import kz.kbtu.artifact.service.repository.UserRepository;
import kz.kbtu.artifact.service.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authManager,
                          JwtService jwtService,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest req) {
        if (userRepository.existsByEmail(req.email())) {
            return ResponseEntity.badRequest().build();
        }
        User user = new User(
                req.email(),
                passwordEncoder.encode(req.password()),
                Set.of(Role.ROLE_USER)
        );
        userRepository.save(user);

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPasswordHash(),
                Set.of(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_USER"))
        );
        return ResponseEntity.ok(new AuthResponse(jwtService.generateToken(userDetails)));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email(), req.password())
        );
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return ResponseEntity.ok(new AuthResponse(jwtService.generateToken(userDetails)));
    }
}