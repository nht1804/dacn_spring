package com.nttu.dacnsv.Controller;


import com.nttu.dacnsv.Request.LoginRequest;
import com.nttu.dacnsv.Request.ServiceResult;
import com.nttu.dacnsv.Service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/Login")
@AllArgsConstructor
public class LoginController {
    public final LoginService loginService;

    @PostMapping
    public ResponseEntity<ServiceResult> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(loginService.login(loginRequest.getUserName(), loginRequest.getPassword()));
    }
    @GetMapping("/Check/{token}")
    public ResponseEntity<ServiceResult> checkLogin(@PathVariable("token") String token) {
        return ResponseEntity.ok().body(loginService.checkLoginToken(token));
    }
}
