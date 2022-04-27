package com.nttu.dacnsv.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nttu.dacnsv.Request.LoginRequest;
import com.nttu.dacnsv.Request.ServiceResult;
import com.nttu.dacnsv.Service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/User/Login")
@AllArgsConstructor
public class LoginController {
    public final LoginService loginService;

    @PostMapping
    public ResponseEntity<ServiceResult> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().body(loginService.login(loginRequest.getUserName(), loginRequest.getPassword()));
    }

    @PostMapping("/Check/{token}")
    public ResponseEntity<ServiceResult> checkLogin(@PathVariable("token") String token) {
        return ResponseEntity.ok().body(loginService.checkLoginToken(token));
    }
}
