package com.example.controllers;

import com.example.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final List<UserDTO> users = List.of(new UserDTO("dima", 1L));

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(users);
    }

    @ExceptionHandler(Throwable.class)
    public String handleAnyException(Throwable ex, HttpServletRequest request) {
        log.error("{}", request.getRequestURL());
        return request.getRequestURI();
    }

}
