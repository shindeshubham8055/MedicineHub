package tda.darkarmy.medicalecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tda.darkarmy.medicalecommerce.dto.LoginRequest;
import tda.darkarmy.medicalecommerce.dto.PasswordResetRequest;
import tda.darkarmy.medicalecommerce.dto.UserDto;
import tda.darkarmy.medicalecommerce.service.UserService;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getALl(){
        return status(200).body(userService.findAll());
    }

    @GetMapping("/logged-in-user")
    public ResponseEntity<?> getLoggedInUser(){
        return status(200).body(userService.getLoggedInUser());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserDto userDto){
        return status(201).body(userService.signup(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
        return status(200).body(userService.login(loginRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody UserDto userDto, @PathVariable("id") Long id){
        return status(200).body(userService.updateUser(userDto, id));
    }

    @PutMapping("/reset-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordResetRequest request){
        return status(200).body(userService.changePassword(request));
    }
//
//    @DeleteMapping("/")
//    public ResponseEntity<?> delete(){
//        return status(200).body(userService.deleteUser());
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id){
        return status(200).body(userService.deleteById(id));
    }


}
