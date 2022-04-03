package com.eksadsupport.minilab.Controller;

import com.eksadsupport.minilab.Common.GenerateJWT;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("ddms/v1/cmd/master/token")
public class JwtController {

    @GetMapping("/generateToken")
    public ResponseEntity<Object> generateToken(){

        Map<String, Object> response = new HashMap<>();
        try{
            String username = "username";

            String token = GenerateJWT.createToken(username);
            response.put("token", token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            response.put("token", "");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
