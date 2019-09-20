package am.arssystems.chat.controller;

import am.arssystems.chat.dto.AuthenticationRequest;
import am.arssystems.chat.dto.AuthenticationResponse;
import am.arssystems.chat.model.User;
import am.arssystems.chat.security.JwtTokenUtil;
import am.arssystems.chat.service.UserServce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserServce userServce;
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    public UserController(UserServce userServce, JwtTokenUtil jwtTokenUtil){
        this.userServce = userServce;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationRequest authenticationRequest){
        User currentUser = userServce.getUserByEmailAndPass(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        AuthenticationResponse responseBody = AuthenticationResponse.builder().message("Bad credentials!").build();
        if(currentUser!=null){
            String token = jwtTokenUtil.generateTokenPassAndId(currentUser.getEmail(), currentUser.getPassword(), currentUser.getId());
            httpStatus = HttpStatus.OK;
            responseBody.setToken(token);
            responseBody.setMessage(null);
        }
        return ResponseEntity.status(httpStatus).body(responseBody);
    }
}
