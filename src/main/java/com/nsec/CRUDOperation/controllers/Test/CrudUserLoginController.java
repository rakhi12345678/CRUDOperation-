package com.nsec.CRUDOperation.controllers.Test;


import com.nsec.CRUDOperation.mapper.ApiResponse;
import com.nsec.CRUDOperation.mapper.LoginRequest;
import com.nsec.CRUDOperation.mapper.UserRequest;
import com.nsec.CRUDOperation.mapper.UserResponse;
import com.nsec.CRUDOperation.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/username")
public class CrudUserLoginController {
    @Autowired
    LoginService loginService;
//http://8080/username/login // "Id": 1791307507904,
    @PostMapping("/login")

    //create profile
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        boolean loginResult = loginService.LoginMethodService(loginRequest);
        if (loginResult == true) {
            ApiResponse apires = new ApiResponse();
            apires.setStatus_code(200);
            apires.setMessage("login succesfully");
            apires.setError(null);
            apires.setResponse("login succesfully");
            return new ResponseEntity<>(apires, HttpStatus.OK);

        }else{
            ApiResponse apires = new ApiResponse();
            apires.setStatus_code(401);
            apires.setMessage("Invalid Credential");
            apires.setError(null);
            apires.setResponse("Invalid login");
            return new ResponseEntity<>(apires, HttpStatus.UNAUTHORIZED);
        }
    }

    //Delete profile
    //http:/localhost:8080/username/delete
    @PatchMapping("/delete/{Id}")
    public ResponseEntity<?> deleteUserProfile(@PathVariable long Id){
        boolean deleteResult = loginService.DeleteMethodService(Id);
        if(deleteResult==true){
            ApiResponse apires = new ApiResponse();
            apires.setStatus_code(200);
            apires.setMessage("delete succesfully");
            apires.setError(null);
            apires.setResponse("delete succesfully");
            return new ResponseEntity<>(apires, HttpStatus.OK);
        }
        else{
            ApiResponse apires = new ApiResponse();
            apires.setStatus_code(204);
            apires.setMessage("Data not found");
            apires.setError(null);
            apires.setResponse("Data not found");
            return new ResponseEntity<>(apires, HttpStatus.NO_CONTENT);

        }

    }

    //Update profile
// http://localhost:8080/username/Update/id
    @PostMapping("/Update/{id}")
    public ResponseEntity<?>updateUserProfile(@PathVariable long Id , @RequestBody UserRequest userRequest){
        UserResponse userRes = loginService.updateUserProfile(userRequest , Id);
        if (userRes != null) {
            ApiResponse apires = new ApiResponse();
            apires.setStatus_code(200);
            apires.setMessage("update succesfully");
            apires.setError(null);
            apires.setResponse(userRes);
            return new ResponseEntity<>(apires, HttpStatus.OK);
        } else {
            ApiResponse apires = new ApiResponse();
            apires.setStatus_code(204);
            apires.setMessage("user get succesfully");
            apires.setError(null);
            apires.setResponse("Null");
            return new ResponseEntity<>(apires, HttpStatus.NO_CONTENT);

        }


    }

}
