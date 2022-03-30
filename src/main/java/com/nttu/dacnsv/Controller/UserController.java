package com.nttu.dacnsv.Controller;

import com.nttu.dacnsv.Model.ServiceResult;
import com.nttu.dacnsv.Model.User;
import com.nttu.dacnsv.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/User")
@AllArgsConstructor
public class UserController {
    public final UserService userService;

    @GetMapping //Get all user from database
    public ResponseEntity<ServiceResult> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("/username/{userName}") //find user by "userName" in database
    public ResponseEntity<ServiceResult> findUserByUserName(@PathVariable("userName") String userName) {
        return ResponseEntity.ok().body(userService.findByUserName(userName));
    }

    @GetMapping("/id/{id}") //find user by "id" in database
    public ResponseEntity<ServiceResult> findUserById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }
    @GetMapping("/role/{roleName}") //find user by "roleName" in database
    public ResponseEntity<ServiceResult> findUserByRoleName(@PathVariable("roleName") String roleName) {
        return ResponseEntity.ok().body(userService.findByRoleName(roleName));
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
