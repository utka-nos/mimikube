package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CommonController {

    @GetMapping("/")
    public String getRoot(
            HttpServletRequest request
    ) {
        String remoteAddr = request.getRemoteAddr();
        return String.format("It is coordinator service! Remote addr is: %s", remoteAddr);
    }

}
