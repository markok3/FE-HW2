package com.example.demo.web;

import com.example.demo.model.User;
import com.example.demo.model.dto.LoginDTO;
import com.example.demo.model.dto.SignUpDTO;
import com.example.demo.model.dto.UserDto;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final AuthenticationManager authenticationManager;

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody SignUpDTO signUpDTO) {
        User user = userService.registerUser(signUpDTO.getUsername(), signUpDTO.getPassword(), signUpDTO.getName(), signUpDTO.getSurname());

        return ResponseEntity.ok(user.toDto());
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginDTO loginReq)  {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
            String username = authentication.getName();
            User user = userService.getUserById(username);
            String token = jwtUtil.createToken(user);
            UserDto userDto = user.toDto();
            userDto.setToken(token);

            return ResponseEntity.ok(userDto);

        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid username or password");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("exception while login");
        }
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable String id) {
        return this.userService.getUserById(id).toDto();
    }
}
