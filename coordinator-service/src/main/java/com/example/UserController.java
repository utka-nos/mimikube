package com.example;

import com.example.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/coordinator/users")
public class UserController {

    private List<UserDTO> users = List.of(
            new UserDTO(),
            new UserDTO()
    );

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(Collections.emptyList());
    }

}
