package am.arssystems.chat.controller;

import am.arssystems.chat.dto.AuthenticationRequest;
import am.arssystems.chat.service.UserServce;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    public UserController(UserServce userServce){
        this.userServce = userServce;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok("");
    }
}
