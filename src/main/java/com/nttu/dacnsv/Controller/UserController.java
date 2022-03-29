package com.nttu.dacnsv.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.MongoException;
import com.nttu.dacnsv.Model.Role;
import com.nttu.dacnsv.Model.ServiceResult;
import com.nttu.dacnsv.Model.User;
import com.nttu.dacnsv.Model.UsersDetail;
import com.nttu.dacnsv.Service.RoleService;
import com.nttu.dacnsv.Service.UserService;
import com.nttu.dacnsv.Test;
import lombok.AllArgsConstructor;
import org.bson.json.Converter;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/User")
@AllArgsConstructor
public class UserController {
    public final UserService userService;
    private Test test;

    @GetMapping //Get all user from database
    public ResponseEntity<ServiceResult> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("/username/{userName}") //find user by "userName" in database
    public ResponseEntity<ServiceResult> findUserByUserName(@PathVariable("userName") String userName) {
        return ResponseEntity.ok().body(userService.findByUserName(userName));
    }

    @PostMapping //add a user to database
    public ResponseEntity<ServiceResult> addUser(@RequestBody User users) {
        return ResponseEntity.ok().body(userService.insert(users));
    }

    @DeleteMapping //Delete a user by ID(string)
    public ResponseEntity<ServiceResult> deleteUser(@RequestBody String id) {
        return ResponseEntity.ok().body(userService.delete(id));
    }

    @PutMapping //update a user
    public ResponseEntity<ServiceResult> updateUser(@RequestBody User user) {
            return ResponseEntity.ok().body(userService.update(user));
    }
}
