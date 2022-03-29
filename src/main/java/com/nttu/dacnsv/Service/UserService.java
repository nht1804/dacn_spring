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

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ServiceResult getAll() {
        ServiceResult result = new ServiceResult();
        result.setData(userRepository.findAll());
        return result;
    }

    public ServiceResult insert(User user) {
        ServiceResult result = new ServiceResult();
        User u = userRepository.findByUserName(user.getUserName()).orElse(null);
        if(u != null){
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("This username is already in use");
        }
        else {
            userRepository.insert(user);
            result.setStatus(ServiceResult.Status.SUCCESS);
            result.setMessage("Success");
        }
        return result;
    }

    public ServiceResult update(User user) {
        ServiceResult result = new ServiceResult();
        if(userRepository.findById(user.getId()).isEmpty()){
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("User Not Found");
        }
        else{
            result.setData(userRepository.save(user));
        }
        return result;
    }

    public ServiceResult delete(String id) {
        ServiceResult result = new ServiceResult();
        User u = userRepository.findById(id).orElse(null);
        if(u == null){
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("User Not Found");
        }
        else{
            userRepository.delete(u);
            result.setMessage("Success");
        }
        return result;
    }

    public ServiceResult findByUserName(String userName) {
        ServiceResult result = new ServiceResult();
        User u = userRepository.findByUserName(userName).orElse(null);
        if(u == null){
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("UserName Not Found");
        }
        else{
            result.setStatus(ServiceResult.Status.SUCCESS);
            result.setMessage("Success");
            result.setData(u);
        }
        return result;
    }

    public ServiceResult findById(String id) {
        ServiceResult result = new ServiceResult();
        User u = userRepository.findById(id).orElse(null);
        if(u == null){
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("UserName Not Found");
        }
        else{
            result.setStatus(ServiceResult.Status.SUCCESS);
            result.setMessage("Success");
            result.setData(u);
        }
        return result;
    }
}
