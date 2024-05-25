package com.bharani.springJwt.Controller;

import com.bharani.springJwt.Modal.AuthenticationResponse;
import com.bharani.springJwt.Modal.User;
import com.bharani.springJwt.Service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")

    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request){
        return ResponseEntity.ok( authenticationService.register(request));
    }

    @PostMapping("/login")

    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
