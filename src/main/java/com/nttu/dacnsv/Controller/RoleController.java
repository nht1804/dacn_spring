package com.nttu.dacnsv.Controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.MongoException;
import com.nttu.dacnsv.Model.Role;
import com.nttu.dacnsv.Service.RoleService;
import com.nttu.dacnsv.Test;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Role")
@AllArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private Test test;

    @GetMapping
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok().body(roleService.getAll());
    }

    @PostMapping //insert a role to database
    public ResponseEntity insertRole(@RequestBody Role role) {
        try {
            if (roleService.findByName(role.getRoleName()).isEmpty()) {
                roleService.insert(role);
                return ResponseEntity.ok().body(test.message("Added a role(" + role.getRoleName() + ") to database"));
            } else {
                return ResponseEntity.ok().body(test.message("this role (" + role.getRoleName() + ") already in database"));
            }
        } catch (MongoException e) {
            return ResponseEntity.ok().body(test.message(e.getMessage()));
        }
    }

    @GetMapping("/name={name}")
    public ResponseEntity findByRoleName(@PathVariable("name") String name) {
        if (roleService.findByName(name).isEmpty()) {
            return ResponseEntity.ok().body("There is no role named" + name + "in the database");
        } else {
            return ResponseEntity.ok().body(roleService.findByName(name));
        }
    }

    @PutMapping //update a role
    public ResponseEntity updateRole(@RequestBody Role role) {
        try {
            Optional<Role> r = roleService.findByName(role.getRoleName());
            if (r.isEmpty()) {
                String roleName = r.get().getRoleName();
                return ResponseEntity.ok().body(test.message("There is no role named" + roleName + "in the database"));
            } else {
                roleService.update(role);
                return ResponseEntity.ok().body(test.message("Updated a role: " + role.getRoleName()));
            }
        } catch (MongoException e) {
            return ResponseEntity.ok().body(test.message(e.getMessage()));
        }
    }

    @DeleteMapping //Delete a role
    public ResponseEntity deleteRole(@RequestBody ObjectNode objectNode) {
        String id = objectNode.get("id").asText();
        try {
            Optional<Role> r = roleService.findByID(id);
            if (r.isEmpty()) {
                return ResponseEntity.ok().body(test.message("Can't find this role in database"));
            } else {
                String roleName = r.get().getRoleName();
                roleService.delete(id);
                return ResponseEntity.ok().body(test.message("deleted a role: " + roleName));
            }
        } catch (MongoException e) {
            return ResponseEntity.ok().body(test.message(e.getMessage()));
        }
    }
}
