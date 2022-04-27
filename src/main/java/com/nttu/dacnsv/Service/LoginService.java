package com.nttu.dacnsv.Service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nttu.dacnsv.Model.User;
import com.nttu.dacnsv.Repository.UserRepository;
import com.nttu.dacnsv.Request.ServiceResult;
import com.nttu.dacnsv.Request.TokenRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;


@Service
@AllArgsConstructor
public class LoginService{
    private final UserRepository userRepository;
    private final String SECRET_KEY = "jlkljkjadywabdsakschasgywadio";
    private final int EXPIRE_TIME = 60000;//milliseconds(60 seconds)
    private final String USERNAME = "userName";
    private final String ROLE = "roleName";

    public ServiceResult login(String userName, String password) {
        ServiceResult result = new ServiceResult();
        User u = userRepository.findByUserNameAndPassword(userName, password).orElse(null);
        if (u != null) {
            try {
                Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
                String token = JWT.create()
                        .withClaim(USERNAME,u.getUserName())
                        .withClaim(ROLE,u.getRole().getRoleName())
                        .withIssuedAt(new Date())
                        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                        .sign(algorithm);
                TokenRequest tokenRequest = new TokenRequest(token);
                result.setMessage("Login Success");
                result.setData(tokenRequest);
            } catch (UnsupportedEncodingException e) {
                result.setStatus(ServiceResult.Status.ERROR);
                result.setMessage(e.toString());
            } catch (JWTCreationException e) {
                result.setStatus(ServiceResult.Status.ERROR);
                result.setMessage(e.toString());
            }
        } else {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("The username or password that you have entered is incorrect.");
        }
        return result;
    }

    public ServiceResult checkLoginToken(String token) {
        ServiceResult result = new ServiceResult();
        if(token!=null){
            try {
                Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
                //Verify token
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT jwt = verifier.verify(token);
                //get value name(username)
                String userName = jwt.getClaim("userName").asString();
                //find user with value(username)
                User u = userRepository.findByUserName(userName).orElse(null);
                if(u!=null){
                    result.setMessage("Login Success");
                }else{
                    result.setStatus(ServiceResult.Status.FAILED);
                    result.setMessage("Can't not find this user");
                }
            } catch (UnsupportedEncodingException e) {
                result.setStatus(ServiceResult.Status.FAILED);
                result.setMessage(e.toString());
            } catch (JWTDecodeException e){
                result.setStatus(ServiceResult.Status.ERROR);
                result.setMessage(e.toString());
            }
            catch (SignatureVerificationException e){
                result.setStatus(ServiceResult.Status.ERROR);
                result.setMessage(e.toString());
            }catch (TokenExpiredException e){
                result.setStatus(ServiceResult.Status.ERROR);
                result.setMessage(e.toString());
            }
        }else{
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("The token is incorrect.");
        }
        return result;
    }
}
