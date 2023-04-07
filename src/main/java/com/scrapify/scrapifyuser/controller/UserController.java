package com.scrapify.scrapifyuser.controller;


import com.scrapify.scrapifyuser.entity.User;
import com.scrapify.scrapifyuser.service.UserService;
import com.scrapify.scrapifyuser.userexception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

        @Autowired
        private UserService userService;

        @PostMapping(value = "/save")
        public ResponseEntity<User> saveUser(@RequestBody User user) throws CustomException {
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
    }

        @RequestMapping(value = "/users", method = RequestMethod.GET)
        public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

        @RequestMapping(value = "/by-id", method = RequestMethod.GET)
        public ResponseEntity<User> findById(@RequestParam("id") String id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

        @RequestMapping(value = "/by-ids", method = RequestMethod.GET)
        public ResponseEntity<List<User>> findByIds(@RequestParam("ids") List<String> ids) {
        return new ResponseEntity<>(userService.findAllById(ids), HttpStatus.OK);
    }

        @RequestMapping(value = "/delete", method = RequestMethod.GET)
        public ResponseEntity<User> delete(@RequestParam("id") String id) throws CustomException {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
