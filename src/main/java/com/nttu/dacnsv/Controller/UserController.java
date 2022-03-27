package com.nttu.dacnsv.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.MongoException;
import com.nttu.dacnsv.Model.Role;
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
    public final RoleService roleService;
    private Test test;

    @GetMapping //Get all user from database
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("/id={userID}") //find user by "userID" in database
    public ResponseEntity getUser(@PathVariable("userID") String userID) {
        if (userService.findByUserID(userID).isEmpty()) {
            return ResponseEntity.ok().body(test.message("Can't find this user"));
        } else {
            return ResponseEntity.ok().body(userService.findByUserID(userID).get());
        }
    }

    @PostMapping //add a user(with role "ROLE_USER" and null information) to database
    public ResponseEntity addUser(@RequestBody User users) {
        try {
            if (userService.findByUserID(users.getUserID()).isEmpty()) {
                //add role("ROLE_USER") to this new user
                users.setRole(roleService.findByName("ROLE_USER").get());
                //add null information for this new user
                users.setInformation(new UsersDetail());
                //add this user to database
                userService.insert(users);
                return ResponseEntity.ok().body(test.message("Added user: " + users.getUserID() + " to database"));
            } else {
                return ResponseEntity.ok().body(test.message("User's id: " + users.getUserID() + " already in the database"));
            }
        } catch (MongoException e) {
            return ResponseEntity.ok().body(test.message(e.getMessage()));
        }
    }

    @DeleteMapping //Delete a user by ID(string)
    public ResponseEntity deleteUser(@RequestBody String id) {
        try {
            if (userService.findById(id).isEmpty()) {
                ResponseEntity.ok().body(test.message("Can't find this user"));
            } else {
                userService.delete(id);
            }
        } catch (MongoException e) {
            return ResponseEntity.ok().body(test.message(e.getMessage()));
        }
        return ResponseEntity.ok().body(test.message("Success"));
    }

    @PutMapping //update a user
    public ResponseEntity updateUser(@RequestBody User user) {
        try {
            if (userService.findById(user.getId()).isEmpty()) {
                return ResponseEntity.ok().body(test.message("Can't find this user"));
            } else {
                userService.update(user);
                return ResponseEntity.ok().body(test.message("Success"));
            }
        } catch (MongoException e) {
            return ResponseEntity.ok().body(test.message(e.getMessage()));
        }
    }

    @PutMapping(value = "/changeRole")
    public ResponseEntity updateUsersRole(@RequestBody ObjectNode objectNode) {
        try {
            String id = objectNode.get("id").asText();
            String roleName = objectNode.get("roleName").asText();
            Optional<User> u = userService.findById(id);

            if (u.isEmpty()) {
                return ResponseEntity.ok().body(test.message("Can't find this user"));
            } else {
                Optional<Role> r = roleService.findByName(roleName);
                if (r.isPresent()) {
                    u.get().setRole(r.get());
                    userService.update(u.get());
                    return ResponseEntity.ok().body(test.message("Changed role to user:" + u.get().getUserID() + " to " + roleName));
                } else {
                    return ResponseEntity.ok().body(test.message("There is no role named " + roleName + " in the database"));
                }
            }
        } catch (MongoException e) {
            return ResponseEntity.ok().body(test.message(e.getMessage()));
        }
    }
}
