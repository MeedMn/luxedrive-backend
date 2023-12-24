package ma.jee.luxedriveBackend.controller;

import ma.jee.luxedriveBackend.config.JwtUtils;
import ma.jee.luxedriveBackend.dto.request.LoginRequest;
import ma.jee.luxedriveBackend.dto.request.SignupRequest;
import ma.jee.luxedriveBackend.dto.response.JwtResponse;
import ma.jee.luxedriveBackend.dto.response.MessageResponse;
import ma.jee.luxedriveBackend.dto.response.UserInfos;
import ma.jee.luxedriveBackend.entity.auth.Authority;
import ma.jee.luxedriveBackend.entity.auth.User;
import ma.jee.luxedriveBackend.entity.enums.Role;
import ma.jee.luxedriveBackend.repository.RoleRepository;
import ma.jee.luxedriveBackend.repository.UserRepository;
import ma.jee.luxedriveBackend.service.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder encoder;
    JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthority().getAuthority();
        User user = userRepository.findById(userDetails.getId()).get();
        UserInfos userInfos = new UserInfos(user.getId(),user.getEmail(),user.getFullName(), user.getAddress(),user.getPhoneNumber());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userInfos,
                userDetails.getUsername(),
                role));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        User user = new User(true,signUpRequest.getFullName(),signUpRequest.getAddress(),signUpRequest.getPhoneNumber(),encoder.encode(signUpRequest.getPassword()),signUpRequest.getEmail());
        Role userRole = Role.valueOf(signUpRequest.getAuthority().toUpperCase());
        Authority authority = roleRepository.findByAuthority(userRole)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        user.setAuthority(authority);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}

