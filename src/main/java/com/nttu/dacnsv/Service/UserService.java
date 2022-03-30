package com.nttu.dacnsv.Service;

import com.nttu.dacnsv.Model.Role;
import com.nttu.dacnsv.Model.ServiceResult;
import com.nttu.dacnsv.Model.UsersDetail;
import com.nttu.dacnsv.Repository.RoleRepository;
import com.nttu.dacnsv.Repository.UserRepository;
import com.nttu.dacnsv.Model.User;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public ServiceResult getAll() {
        ServiceResult result = new ServiceResult();
        result.setData(userRepository.findAll());
        return result;
    }

    public ServiceResult insert(User user) {
        ServiceResult result = new ServiceResult();
        User u = userRepository.findByUserName(user.getUserName()).orElse(null);
        if (u != null) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("This username is already in use");
        } else {
            //add user with role default is ROLE_USER
            Role r = roleRepository.findByRoleName("ROLE_USER").orElse(null);
            user.setRole(r);
            //add user with date of birth default is now
            UsersDetail infor = new UsersDetail();
            user.setInformation(infor);
            //add user
            userRepository.insert(user);
            result.setMessage("Success");
        }
        return result;
    }

    public ServiceResult update(User user) {
        ServiceResult result = new ServiceResult();
        if (userRepository.findById(user.getId()).isEmpty()) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("User Not Found");
        } else {
            //update role by id for user
            Role r = roleRepository.findById(user.getRole().getId()).orElse(null);
            if(r==null){
                result.setStatus(ServiceResult.Status.FAILED);
                result.setMessage("Role Not Found");
            }else {
                user.setRole(r);
                userRepository.save(user);
                result.setMessage("Success");
            }
        }
        return result;
    }

    public ServiceResult delete(String id) {
        ServiceResult result = new ServiceResult();
        User u = userRepository.findById(id).orElse(null);
        if (u == null) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("User Not Found");
        } else {
            userRepository.delete(u);
            result.setMessage("Success");
        }
        return result;
    }

    public ServiceResult findByUserName(String userName) {
        ServiceResult result = new ServiceResult();
        User u = userRepository.findByUserName(userName).orElse(null);
        if (u == null) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("UserName Not Found");
        } else {
            result.setMessage("Success");
            result.setData(u);
        }
        return result;
    }

    public ServiceResult findById(String id) {
        ServiceResult result = new ServiceResult();
        User u = userRepository.findById(id).orElse(null);
        if (u == null) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("UserName Not Found");
        } else {
            result.setMessage("Success");
            result.setData(u);
        }
        return result;
    }
    public ServiceResult findByRoleName(String roleName) {
        ServiceResult result = new ServiceResult();
        List<User> u = userRepository.findUserByRoleName(roleName);
        if (u.isEmpty()) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Role Not Found");
        } else {
            result.setMessage("Success");
            result.setData(u);
        }
        return result;
    }
}
