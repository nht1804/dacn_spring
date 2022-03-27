package com.nttu.dacnsv.Service;

import com.nttu.dacnsv.Model.Role;
import com.nttu.dacnsv.Repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role insert(Role role) {
        return roleRepository.insert(role);
    }

    public List<Role> getAll() {
        return roleRepository.findAll();
    }
    public Optional<Role> findByName(String name){
        return roleRepository.findByRoleName(name);
    }
    public Role update(Role role) {
        return roleRepository.save(role);
    }

    public void delete(String id) {
        roleRepository.deleteById(id);
    }

    public Optional<Role> findByID(String id) {
        return roleRepository.findById(id);
    }

    public List<Role> findByLevel(int level) {
        return roleRepository.findByLevel(level);
    }
}
