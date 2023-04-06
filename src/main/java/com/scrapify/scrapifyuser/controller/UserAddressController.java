package com.scrapify.scrapifyuser.controller;


import com.scrapify.scrapifyuser.entity.User;
import com.scrapify.scrapifyuser.entity.UserAddress;
import com.scrapify.scrapifyuser.service.UserAddressService;
import com.scrapify.scrapifyuser.userexception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user-address")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @PostMapping(value = "/save")
    public ResponseEntity<UserAddress> saveUserAddress(@RequestBody UserAddress userAddress) throws CustomException {
        return new ResponseEntity<UserAddress>(userAddressService.saveUserAddress(userAddress), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user-address", method = RequestMethod.GET)
    public ResponseEntity<List<UserAddress>> getAllUserAddress() {
        return new ResponseEntity<>(userAddressService.getAllUserAddress(), HttpStatus.OK);
    }

//    @RequestMapping(value = "/by-id", method = RequestMethod.GET)
//    public ResponseEntity<UserAddress> findById(@RequestParam("id") String id) {
//        return new ResponseEntity<>(userAddressService.findById(id), HttpStatus.OK);
//    }

    @GetMapping("/by-latitude")
    public ResponseEntity<UserAddress> findNearByUser(@RequestParam ("latitude") Double latitude ,@RequestParam("longitude") Double longitude){
        return new ResponseEntity<>(userAddressService.findNearByUser(latitude, longitude),HttpStatus.OK);
    }
}
