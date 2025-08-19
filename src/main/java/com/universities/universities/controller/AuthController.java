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

import com.universities.universities.dto.CreateUserDTO;
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

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDto){
        

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUserName(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            

            return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Incorrect Credentials", HttpStatus.NOT_FOUND);
        } catch (AuthenticationException e){
            return new ResponseEntity<>("Error During Authentication", HttpStatus.CONFLICT);
        }

        
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody CreateUserDTO createUserDto){

        // add check for username exists in a DB
        if(userRepository.existsByUserName(createUserDto.getUserName())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(userRepository.existsByEmail(createUserDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        UserDTO userdto = new UserDTO();
        userdto.setName(createUserDto.getName());
        userdto.setUserName(createUserDto.getUserName());
        userdto.setEmail(createUserDto.getEmail());
        userdto.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        userdto.setRol("ROLE_ADMIN");

        
        userRepository.save(convertToEntity(userdto));
        userdto = null;

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    //methods for convert entities or dto's
    
    private User convertToEntity(UserDTO userDTO){
        User ranking = modelMapper.map(userDTO, User.class);
        return ranking;
    }

    /* private UserDTO convertToDto(User user){
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    } */
}
