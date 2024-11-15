package com.linkedin.user_service.controller;

import com.linkedin.user_service.dto.LoginRequestDTO;
import com.linkedin.user_service.dto.SignUpRequestDTO;
import com.linkedin.user_service.dto.UserDTO;
import com.linkedin.user_service.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody @Valid SignUpRequestDTO signUpRequestDTO){
        UserDTO userDTO = authService.signUp(signUpRequestDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO){
        String token = authService.login(loginRequestDTO);
        return  ResponseEntity.ok(token);
    }
}
