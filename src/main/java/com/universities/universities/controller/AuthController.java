package com.universities.universities.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universities.universities.configuration.JwtTokenProvider;
import com.universities.universities.dto.CreateUserDTO;
import com.universities.universities.dto.JwtAuthResponseDTO;
import com.universities.universities.dto.LoginDTO;
import com.universities.universities.dto.UserDTO;
import com.universities.universities.model.User;
import com.universities.universities.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signin")
    public ResponseEntity authenticateUser(@RequestBody LoginDTO loginDto){
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginDto.getUserName(), 
                    loginDto.getPassword()
                )
            );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            // Generar JWT token
            String token = jwtTokenProvider.generateToken(authentication);
            
            // Obtener datos del usuario
            User user = userRepository.findByUserName(loginDto.getUserName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
            JwtAuthResponseDTO response = new JwtAuthResponseDTO(
                token, 
                user.getUserName(), 
                user.getEmail(), 
                user.getRol(),
                user.getName()
            );
            
            return ResponseEntity.ok(response);
            
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Credenciales incorrectas", HttpStatus.UNAUTHORIZED);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Error durante la autenticación", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity registerUser(@RequestBody CreateUserDTO createUserDto){
        // Verificar si el usuario ya existe
        if(userRepository.existsByUserName(createUserDto.getUserName())){
            return new ResponseEntity<>("¡El nombre de usuario ya está en uso!", 
                                      HttpStatus.BAD_REQUEST);
        }

        // Verificar si el email ya existe
        if(userRepository.existsByEmail(createUserDto.getEmail())){
            return new ResponseEntity<>("¡El email ya está en uso!", 
                                      HttpStatus.BAD_REQUEST);
        }

        // Crear nuevo usuario
        UserDTO userdto = new UserDTO();
        userdto.setName(createUserDto.getName());
        userdto.setUserName(createUserDto.getUserName());
        userdto.setEmail(createUserDto.getEmail());
        userdto.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        userdto.setRol("ROLE_ADMIN");
        
        userRepository.save(convertToEntity(userdto));
        
        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
    }

    private User convertToEntity(UserDTO userDTO){
        User user = modelMapper.map(userDTO, User.class);
        return user;
    }
}
