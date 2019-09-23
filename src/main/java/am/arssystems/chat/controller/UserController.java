package am.arssystems.chat.controller;

import am.arssystems.chat.dto.AuthenticationRequest;
import am.arssystems.chat.dto.AuthenticationResponse;
import am.arssystems.chat.dto.responseWrapper.UserData;
import am.arssystems.chat.model.User;
import am.arssystems.chat.model.view.Views;
import am.arssystems.chat.security.CurrentUser;
import am.arssystems.chat.security.JwtTokenUtil;
import am.arssystems.chat.service.UserServce;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserServce userServce;
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    public UserController(UserServce userServce, JwtTokenUtil jwtTokenUtil) {
        this.userServce = userServce;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        User currentUser = userServce.getUserByEmailAndPass(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        AuthenticationResponse responseBody = AuthenticationResponse.builder().message("Bad credentials!").build();
        if (currentUser != null) {
            String token = jwtTokenUtil.generateTokenPassAndId(currentUser.getEmail().toLowerCase(), currentUser.getPassword(), currentUser.getId());
            httpStatus = HttpStatus.OK;
            responseBody.setToken(token);
            responseBody.setMessage(null);
        }
        return ResponseEntity.status(httpStatus).body(responseBody);
    }

    @GetMapping("/searchByFirstNameOrEmal")
    @JsonView(Views.Special.class)
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity searchUserByEmalOrFirtName(@AuthenticationPrincipal CurrentUser currentUser,
                                                     @RequestParam(name = "text") String text,
                                                     @RequestParam(name = "groupId", required = false, defaultValue = "0") int groupId) {
        List<UserData> result = userServce.searchUser(currentUser.getUser(),text,groupId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/myGroup")
    @PreAuthorize("hasAuthority('user')")
    @JsonView(Views.Base.class)
    public ResponseEntity getUserGroup(@AuthenticationPrincipal CurrentUser currentUser){
        return ResponseEntity.ok(userServce.getUserGroups(currentUser.getUser()));
    }
}
