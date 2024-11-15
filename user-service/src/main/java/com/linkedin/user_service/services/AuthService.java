package com.linkedin.user_service.services;

import com.linkedin.user_service.dto.LoginRequestDTO;
import com.linkedin.user_service.dto.SignUpRequestDTO;
import com.linkedin.user_service.dto.UserDTO;
import com.linkedin.user_service.entity.User;
import com.linkedin.user_service.entity.Person;
import com.linkedin.user_service.exception.BadRequestException;
import com.linkedin.user_service.exception.ResourceNotFoundException;
import com.linkedin.user_service.repository.UserNodeRepository;
import com.linkedin.user_service.repository.UserRepository;
import com.linkedin.user_service.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final UserNodeRepository userNodeRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    @Transactional
    public UserDTO signUp(SignUpRequestDTO signUpRequestDTO) {

        boolean exists = userRepository.existsByEmail(signUpRequestDTO.getEmail());
        if(exists){
            throw new BadRequestException("User already exists, cannot signup again");
        }
        User user = modelMapper.map(signUpRequestDTO, User.class);
        user.setPassword(PasswordUtil.hashPassword(signUpRequestDTO.getPassword()));

        // Save the new user to PostgreSQL database
        User savedUser = userRepository.save(user);

        // Save user node to Neo4j using Neo4j transaction manager
        saveUserInNeo4j(savedUser);

        return modelMapper.map(savedUser,UserDTO.class);

    }

    public String login(LoginRequestDTO loginRequestDTO) {

        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with email: "+loginRequestDTO.getEmail()));

        boolean isPasswordMatch = PasswordUtil.checkPassword(loginRequestDTO.getPassword(), user.getPassword());

        if(!isPasswordMatch){
            throw new BadRequestException("Incorrect password");
        }

        return jwtService.generateAccessToken(user);

    }

    @Transactional(transactionManager = "neo4jTransactionManager")
    private void saveUserInNeo4j(User savedUser) {
        Person person = new Person(savedUser.getId(), savedUser.getName());
        userNodeRepository.save(person);
    }
}
