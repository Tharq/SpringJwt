package com.bharani.springJwt.Service;

import com.bharani.springJwt.Modal.AuthenticationResponse;
import com.bharani.springJwt.Modal.User;
import com.bharani.springJwt.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    MailService mailService;

    public AuthenticationResponse register(User request){



        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        user = userRepository.save(user);

        String token = jwtService.generateToken(user);

        mailService.sendMail(request.getUsername(),"Jwt token","Token for Login(Valid up to 24hrs): "+token);

        return new AuthenticationResponse(token);


    }
    public AuthenticationResponse authenticate(User request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()->new UsernameNotFoundException("user not found"));
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}
