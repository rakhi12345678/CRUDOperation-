package com.nsec.CRUDOperation.controllers.Test;

import com.nsec.CRUDOperation.mapper.ApiResponse;
import com.nsec.CRUDOperation.mapper.UserRequest;
import com.nsec.CRUDOperation.mapper.UserResponse;
import com.nsec.CRUDOperation.model.RegUser;
import com.nsec.CRUDOperation.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class CrudUser {

    @Autowired
    UserService userService;

    @PostMapping("/post")
    public ResponseEntity<?> postUser(@RequestBody UserRequest userRequest) {

        UserResponse userResponse = userService.postUserService(userRequest);

        // System.out.println(userResponse);
        ApiResponse apires = new ApiResponse();
        apires.setStatus_code(201);
        apires.setMessage("Register Succesfully");
        apires.setError(null);
        apires.setResponse(userResponse);
        return new ResponseEntity<>(apires, HttpStatus.CREATED);
    }

    //http://localhost:8080/user/getAll

    @GetMapping("/getAll")

    public ResponseEntity<?> getAllUser() {
        List<RegUser> userResponse = userService.getAllUser();
        ApiResponse apires = new ApiResponse();
        apires.setStatus_code(200);
        apires.setMessage("User get succesfully");
        apires.setError(null);

        apires.setResponse(userResponse);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);


    }

    //http://localhost:8080/user/1791225841834


    @GetMapping("/{ID}")
    public ResponseEntity<?> getuserById(@PathVariable("ID") long Id) {
       RegUser userResponse = userService.getUserById(Id);
        if (userResponse != null) {
            ApiResponse apires = new ApiResponse();
            apires.setStatus_code(200);
            apires.setMessage("user get succesfully");
            apires.setError(null);
            apires.setResponse(userResponse);
            return new ResponseEntity<>(apires, HttpStatus.OK);
        } else {
            ApiResponse apires = new ApiResponse();
            apires.setStatus_code(204);
            apires.setMessage("user get succesfully");
            apires.setError(null);
            apires.setResponse(userResponse);
            return new ResponseEntity<>(apires, HttpStatus.NO_CONTENT);

        }
    }
}

    /*    if (userResponse == null) {
            apires.setStatus_code(409);
            apires.setMessage("Invalid ID");
            apires.setError(null);
            apires.setResponse(null);
            return new ResponseEntity<>(apires, HttpStatus.CONFLICT);

        }*/

       /* apires.setStatus_code(200);
        apires.setMessage("User Data Get Succesfully");
        apires.setError(null);

        apires.setResponse(userResponse);
        return new ResponseEntity<>(apires, HttpStatus.OK);

    }
    }*/


