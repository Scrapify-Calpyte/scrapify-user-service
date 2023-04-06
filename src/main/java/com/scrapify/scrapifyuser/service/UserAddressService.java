package com.scrapify.scrapifyuser.service;

import com.scrapify.scrapifyuser.entity.UserAddress;
import com.scrapify.scrapifyuser.userexception.CustomException;

import java.util.List;

public interface UserAddressService {

    UserAddress saveUserAddress(UserAddress userAddress) throws CustomException;

    List<UserAddress> getAllUserAddress();

   // UserAddress findById(String id);

    UserAddress findNearByUser(Double latitude, Double longitude);

}
