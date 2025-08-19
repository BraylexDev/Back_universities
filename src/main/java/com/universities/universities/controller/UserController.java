package com.universities.universities.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.universities.universities.dto.LoginDTO;
import com.universities.universities.dto.UserDTO;
import com.universities.universities.dto.UserLoginDTO;
import com.universities.universities.model.User;
import com.universities.universities.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;
    
    @ResponseStatus(HttpStatus.CREATED) //201
    @PostMapping("/create")
    public UserDTO create(UserDTO userDTO){
        userDTO.setPassword(encryptedPassword(userDTO.getPassword()));
        User user = convertToEntity(userDTO);
        /* User userCreated = userService.save(user); */
        return convertToDto(user);
    }

    @GetMapping("/user/{userName}")
    public UserDTO findByUserNameAndPassword(String userName, String password){
        User user = userService.findByUserNameAndPassword(userName, password);
        return convertToDto(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO request) {
        
        boolean valido = userService.validarCredenciales(request.getUserName(), request.getPassword());

        UserLoginDTO userLog = new UserLoginDTO();
        
        if (valido) {
            Optional<User> userdata = Optional.of(new User());
            userdata = userService.findByUserName(request.getUserName());
            userLog.setEmail(userdata.get().getEmail());
            userLog.setName(userdata.get().getName());
            userLog.setRol(userdata.get().getRol());
            
            return ResponseEntity.ok().body(userLog);
        } else {
            return ResponseEntity.status(401).body(userLog);
        }
    }


    //method for Encrypted Password

    public String encryptedPassword(String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    //methods for convert entities or dto's
    
    private User convertToEntity(UserDTO userDTO){
        User ranking = modelMapper.map(userDTO, User.class);
        return ranking;
    }

    private UserDTO convertToDto(User user){
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }
}
