package com.example.controllers;

import com.example.dto.UserDTO;
import com.example.model.User;
import com.example.model.UserMapper;
import com.example.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserRepo userRepo;

    UserMapper userMapper = new UserMapper();

    @GetMapping("/users/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        log.info("users/all");
        return ResponseEntity.ok(
                userRepo.findAll()
                        .stream()
                        .map(userMapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/users")
    public UserDTO addNewUser(
            @RequestBody UserDTO userDTO
    ) {
        User savedUser = userRepo.save(userMapper.toEntity(userDTO));
        return userMapper.toDto(savedUser);
    }

    @GetMapping("/")
    public String welcomePage(
            HttpServletRequest request
    ) {
        String remoteAddr = request.getRemoteAddr();
        String localAddr = request.getLocalAddr();
        return String.format(
                "user service. remote addr: %s, local addr: %s",
                remoteAddr,
                localAddr
        );

    }

}
