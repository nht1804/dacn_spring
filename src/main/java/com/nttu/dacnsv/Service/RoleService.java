package com.nttu.dacnsv.Service;

import com.nttu.dacnsv.Model.Role;
import com.nttu.dacnsv.Model.ServiceResult;
import com.nttu.dacnsv.Repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public ServiceResult insert(Role role) {
        ServiceResult result = new ServiceResult();
        Role r = roleRepository.findByRoleName(role.getRoleName()).orElse(null);
        if (r != null) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("This RoleName is already in database");
        } else {
            roleRepository.insert(role);
            result.setStatus(ServiceResult.Status.SUCCESS);
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
            result.setStatus(ServiceResult.Status.SUCCESS);
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
            result.setStatus(ServiceResult.Status.SUCCESS);
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
            result.setStatus(ServiceResult.Status.SUCCESS);
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
            result.setStatus(ServiceResult.Status.SUCCESS);
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
            result.setStatus(ServiceResult.Status.SUCCESS);
            result.setMessage("Success");
            result.setData(r);
        }
        return result;
    }
}
