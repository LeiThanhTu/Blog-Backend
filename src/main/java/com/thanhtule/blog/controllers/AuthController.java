package com.thanhtule.blog.controllers;

import com.thanhtule.blog.entities.User;
import com.thanhtule.blog.exceptions.ApiException;
import com.thanhtule.blog.payloads.JwtAuthRequest;
import com.thanhtule.blog.payloads.JwtAuthResponse;
import com.thanhtule.blog.payloads.UserDto;
import com.thanhtule.blog.repositories.UserRepo;
import com.thanhtule.blog.security.JwtTokenHelper;
import com.thanhtule.blog.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws ApiException {
        this.authenticate(request.getUsername(), request.getPassword());
        
        // Load user details from database to get the complete user object
        User user = this.userRepo.findByEmail(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + request.getUsername()));
        
        // Generate token with user details
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtTokenHelper.generateToken(userDetails);

        // Create response
        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        response.setUser(this.mapper.map(user, UserDto.class));
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws ApiException{

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);

        try {

            this.authenticationManager.authenticate(authenticationToken);

        } catch (BadCredentialsException e) {
            System.out.println("Invalid Detials !!");
            throw new ApiException("Invalid username or password !!");
        }catch(Exception e) {
            throw new ApiException("Internal Server Error !!");
        }

    }

     //register new user api

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
        UserDto registeredUser = this.userService.registerNewUser(userDto);
        return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
    }

//    @GetMapping("/current-user/")
//    public ResponseEntity<UserDto> getUser(Principal principal) {
//        User user = this.userRepo.findByEmail(principal.getName()).get();
//        return new ResponseEntity<UserDto>(this.mapper.map(user, UserDto.class), HttpStatus.OK);
//    }

}