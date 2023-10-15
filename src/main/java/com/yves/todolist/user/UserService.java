package com.yves.todolist.user;

import java.util.Collections;
import java.util.Map;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private IUserRepository userRepository;

    public ResponseEntity<?> saveUserModel(UserModel userModel){
        var user = userRepository.findByUsername(userModel.getUsername());

        if(user != null){
             Map<String, String> errorResponse = Collections.singletonMap("error", "User already exists");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        var passwordHashed = BCrypt.withDefaults()
        .hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashed);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.ok().body(userCreated);
    }
}
