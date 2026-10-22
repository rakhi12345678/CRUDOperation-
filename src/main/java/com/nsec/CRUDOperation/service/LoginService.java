package com.nsec.CRUDOperation.service;

import com.nsec.CRUDOperation.mapper.LoginRequest;
import com.nsec.CRUDOperation.mapper.UserResponse;
import com.nsec.CRUDOperation.model.RegUser;
import com.nsec.CRUDOperation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service

public class LoginService {
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String dateString = currentDateTime.format(formatter);

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;


    public boolean LoginMethodService(LoginRequest loginRequest) {
        //Form LoginRequest Data
        long Id = loginRequest.getId();
        String password = loginRequest.getPassword();

        // Database store login data

        RegUser userResponse = userService.getUserById(Id);
        if (userResponse != null) {
            if (Id == userResponse.getId() && password.equals(userResponse.getPassword())) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    //for delete profile service method
    public boolean DeleteMethodService(long Id) {
        RegUser userResponse = userService.getUserById(Id);

        if (userResponse != null) {
            RegUser regUser = userResponse;
            if (regUser.isStatus() == true) {
                regUser.setStatus(false);
                regUser.setDeleteDate(dateString);
                // System.out.println(regUser);
                userRepository.save(regUser);

                return true;
            } else {
                return false;
            }


        } else {
            return false;
        }

    }

    //Update profile
    public UserResponse updateUserProfile(LoginRequest userRequest , long Id) {
        RegUser idUser = userService.getUserById(Id);
        UserResponse user = new UserResponse();
        if (idUser != null) {
            user.setEmail(userRequest.getEmail());
            user.setId(idUser.getId());
            user.setName(userRequest.getName());
            user.setPassword(userRequest.getPassword());
            user.setMobileNo(userRequest.getMobileNo());

            //
            RegUser user1 = new RegUser();
            user1.setId(idUser.getId());
            user1.setName(userRequest.getName());
            user1.setEmail(userRequest.getEmail());
            user1.setMobileNo(userRequest.getMobileNo());
            user1.setPassword(userRequest.getPassword());
            user1.setCreatedDate(idUser.getCreatedDate());
            user1.setModifiedDate(dateString);
            user1.setDeleteDate(null);
            user1.setStatus(true);
            userRepository.save(user1);

        } else {
            return null;
        }
        return user;
    }

        //  System.out.println(user);


}


