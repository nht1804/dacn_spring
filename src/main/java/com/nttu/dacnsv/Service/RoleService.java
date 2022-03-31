package com.nttu.dacnsv.Service;

import com.nttu.dacnsv.Model.Role;
import com.nttu.dacnsv.Model.User;
import com.nttu.dacnsv.Request.ServiceResult;
import com.nttu.dacnsv.Repository.RoleRepository;
import com.nttu.dacnsv.Repository.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public ServiceResult insert(Role role) {
        ServiceResult result = new ServiceResult();
        Role r = roleRepository.findByRoleName(role.getRoleName()).orElse(null);
        if (r != null) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("This RoleName is already in database");
        } else {
            roleRepository.insert(role);
            result.setMessage("Success");
        }
        return result;
    }

    public ServiceResult getAll() {
        ServiceResult result = new ServiceResult();
        result.setData(roleRepository.findAll());
        return result;
    }

    public ServiceResult update(Role role) {
        ServiceResult result = new ServiceResult();
        if (roleRepository.findById(role.getId()).isEmpty()) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Role Not Found");
        } else {
            roleRepository.save(role);
            //update user with this role id
            List<User> u = userRepository.findUserByRoleId(role.getId());
            if (!u.isEmpty()) {
                u.forEach(e -> {
                    e.setRole(role);
                    userRepository.save(e);
                });
            }
            result.setMessage("Success");
        }
        return result;
    }

    public ServiceResult delete(String id) {
        ServiceResult result = new ServiceResult();
        Role r = roleRepository.findById(id).orElse(null);
        if (r == null) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Role Not Found");
        } else {
            roleRepository.delete(r);
            result.setMessage("Success");
        }
        return result;
    }

    public ServiceResult findByID(String id) {
        ServiceResult result = new ServiceResult();
        Role r = roleRepository.findById(id).orElse(null);
        if (r == null) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Role Not Found");
        } else {
            result.setMessage("Success");
            result.setData(r);
        }
        return result;
    }

    public ServiceResult findByLevel(int level) {
        ServiceResult result = new ServiceResult();
        List<Role> r = roleRepository.findByLevel(level);
        if (r.isEmpty()) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Role Level Not Found");
        } else {
            result.setMessage("Success");
            result.setData(r);
        }
        return result;
    }

    public ServiceResult findByName(String name) {

        ServiceResult result = new ServiceResult();
        Role r = roleRepository.findByRoleName(name).orElse(null);
        if (r == null) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Role Name Not Found");
        } else {
            result.setMessage("Success");
            result.setData(r);
        }
        return result;
    }
}
