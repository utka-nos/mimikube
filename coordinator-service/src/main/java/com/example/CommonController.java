package com.example;

import org.apache.catalina.connector.Request;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

    @GetMapping("/")
    public String getRoot() {
        return "ok: /";
    }

    @GetMapping("/users")
    public String getUsers() {
        return "ok: /users";
    }

}
