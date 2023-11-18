//package com.azri.library.controller;
//
//import com.azri.library.entity.User;
//import com.azri.library.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/user") //TODO api /v1/ ....
//@RequiredArgsConstructor
//public class UserController {
//    @Autowired
//    private UserService userService;
////    @Autowired
////    private AuthenticationManager authenticationManager;
//
//
//    public String index() {
//        return "index";
//    }
//
//    @PostMapping("/register")
//    public User addNewUser(@RequestBody User user) {
//        return userService.saveUser(user);
//    }
//
////    @PostMapping("/token")
////    public String getToken(@RequestBody AuthRequest authRequest) {
////        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
////        if (authenticate.isAuthenticated()) {
////            return userService.generateToken(authRequest.getUsername());
////        } else {
////            throw new RuntimeException("invalid access");
////        }
////    }
////    @GetMapping("/validate")
////    public String validateToken(@RequestParam("token") String token) {
////        userService.validateToken(token);
////        return "Token is valid";
////    }
//}
