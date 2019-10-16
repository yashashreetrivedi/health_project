package io.catalyte.health_api.controllers;

import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.catalyte.health_api.domain.User;
import io.catalyte.health_api.payload.ApiResponse;
import io.catalyte.health_api.payload.JwtAuthenticationResponse;
import io.catalyte.health_api.payload.LoginRequest;
//import io.catalyte.health_api.payload.SignUpRequest;
//import io.catalyte.sports.store.domain.User;
//import io.catalyte.sports.store.payload.*;
//import io.catalyte.sports.store.repository.UserRepository;
//import io.catalyte.sports.store.security.JwtTokenProvider;
import io.catalyte.health_api.repositories.UserRepository;
import io.catalyte.health_api.security.JwtTokenProvider;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	
	@Autowired
    AuthenticationManager authenticationManager;
	
	@Autowired
    JwtTokenProvider tokenProvider;
	
	
	@PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        
 
       
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}
	

	

	

	
}
