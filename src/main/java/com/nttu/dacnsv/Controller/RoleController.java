package com.nttu.dacnsv.Controller;

import com.nttu.dacnsv.Model.Role;
import com.nttu.dacnsv.Request.DeleteByIdRequest;
import com.nttu.dacnsv.Request.ServiceResult;
import com.nttu.dacnsv.Service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/Role")
@AllArgsConstructor
public class RoleController {
    private final RoleService roleService;
    @GetMapping
    public ResponseEntity<ServiceResult> getRoles() {
        return ResponseEntity.ok().body(roleService.getAll());
    }

    @PostMapping //insert a role to database
    public ResponseEntity<ServiceResult> insertRole(@RequestBody Role role) {
        return ResponseEntity.ok().body(roleService.insert(role));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ServiceResult> findByRoleName(@PathVariable("name") String name) {
        return ResponseEntity.ok().body(roleService.findByName(name));
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<ServiceResult> findByRoleLevel(@PathVariable("level") int level) {
        return ResponseEntity.ok().body(roleService.findByLevel(level));
    }

    @PutMapping //update a role
    public ResponseEntity<ServiceResult> updateRole(@RequestBody Role role) {
        return ResponseEntity.ok().body(roleService.update(role));
    }

    @DeleteMapping //Delete a role
    public ResponseEntity<ServiceResult> deleteRole(@RequestBody DeleteByIdRequest request) {
        return ResponseEntity.ok().body(roleService.delete(request.getId()));
    }
}
