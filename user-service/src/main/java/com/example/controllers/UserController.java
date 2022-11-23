package com.example.controllers;

import com.example.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class UserController {

    private final List<UserDTO> users = List.of(new UserDTO("dima", 1L));

    @GetMapping("/users/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        log.info("users/all");
        return ResponseEntity.ok(users);
    }

}
