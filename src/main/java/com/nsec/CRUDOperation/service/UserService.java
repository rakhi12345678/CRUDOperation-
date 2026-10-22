package com.nsec.CRUDOperation.service;

import com.nsec.CRUDOperation.mapper.UserRequest;
import com.nsec.CRUDOperation.mapper.UserResponse;
import com.nsec.CRUDOperation.model.RegUser;
import com.nsec.CRUDOperation.model.RegUser;
import com.nsec.CRUDOperation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Service


public class UserService {

    LocalDateTime currentDateTime= LocalDateTime.now();
    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String dateString=currentDateTime.format(formatter);

    @Autowired
  /*  private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/
UserRepository userRepository;
    public UserResponse postUserService(UserRequest userRequest) {

        UserResponse user = new UserResponse();
        user.setEmail(userRequest.getEmail());
        user.setId(System.currentTimeMillis());
        user.setName(userRequest.getName());
        user.setPassword(userRequest.getPassword());
        user.setMobileNo(userRequest.getMobileNo());
      //  System.out.println(user);

        RegUser user1= new RegUser();
        user1.setId(System.currentTimeMillis());
        user1.setName(userRequest.getName());
        user1.setEmail(userRequest.getEmail());
        user1.setMobileNo(userRequest.getMobileNo());
        user1.setPassword(userRequest.getPassword());
        user1.setCreatedDate(dateString);
        user1.setModifiedDate(dateString);
        user1.setDeleteDate(null);
        user1.setStatus(true);

        userRepository.save(user1);

        return user;

    }


    public List<RegUser> getAllUser() {

        List<RegUser>getAllUsers = userRepository.findAll();
        return getAllUsers;
    }

    public RegUser getUserById(Long Id) {

        Optional<RegUser> getUserById = userRepository.findById(Id);
        if(getUserById.isPresent()) {
            RegUser regUser = getUserById.get();
            if (regUser.isStatus() == true) {
                return regUser;
            } else {
                return null;
            }
        }else{
            return null;
        }

    }
}
