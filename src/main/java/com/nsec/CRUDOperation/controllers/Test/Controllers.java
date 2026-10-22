package com.nsec.CRUDOperation.controllers.Test;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
 @RequestMapping("/test")
    public class Controllers {

     //http://localhost:8080/test/getname
        @GetMapping("/getname")
        public ResponseEntity<?> getHelloMethod(){  //make function
            String str = "Rakhi";
            Map<String,String> apiRes = new HashMap<>();
            apiRes.put("Name", str);
            return new ResponseEntity<>(apiRes, HttpStatusCode.valueOf(200));
        }

        //http://localhost:8080/test/hello
        @GetMapping("/hello")
        public ResponseEntity<String> helloMethod(){
            String str = "Hello world";

            return new ResponseEntity<>(str, HttpStatusCode.valueOf(200));
        }


    }

