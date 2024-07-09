package com.sfbramos.jwtsecurity.controllers.v1;

import com.sfbramos.jwtsecurity.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/demo")
public class DemoController {

    private final UserService userService;

    @GetMapping()
    public <T>ResponseEntity<T> helloWorld() {
        return ResponseEntity.ok((T)"hello from secured endpoint!");
    }
}
