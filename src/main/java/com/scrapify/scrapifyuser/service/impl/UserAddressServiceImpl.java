package com.scrapify.scrapifyuser.service.impl;

import com.scrapify.scrapifyuser.entity.UserAddress;
import com.scrapify.scrapifyuser.repo.UserAddressRepository;
import com.scrapify.scrapifyuser.service.UserAddressService;
import com.scrapify.scrapifyuser.userexception.CustomException;
import com.scrapify.scrapifyuser.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserAddressServiceImpl implements UserAddressService{


    @Autowired
    private UserAddressRepository userAddressRepository;


    @Override
    public UserAddress saveUserAddress(UserAddress userAddress) throws CustomException {
        Mapper.setAuditable(userAddress);
        userAddressRepository.save(userAddress);
        return userAddress;
    }

    @Override
    public List<UserAddress> getAllUserAddress() {
        List<UserAddress> usersList = userAddressRepository.findAll();
        return usersList.stream().map(user -> Mapper.map(user, UserAddress.class)).collect(Collectors.toList());
    }

    @Override
    public UserAddress findById(String id) {
        Optional<UserAddress> userAddressOptional = userAddressRepository.findById(id);
        return userAddressOptional.orElse(null);
    }

//    @Override
//    public UserAddress findById(String id) {
//        Optional<UserAddress> userOptional = userAddressRepository.findById(id);
//        if (userOptional.isPresent()) {
//            UserAddress user = new UserAddress();
//            user.setId(userOptional.get().getId());
//            user.setLatitude(userOptional.get().getLatitude());
//            return user;
//        }
//        return null;
//    }

    @Override
    public UserAddress findNearByUser(Double latitude, Double longitude) {
        return userAddressRepository.findByLatitudeAndLongitude(latitude,longitude);
    }


}
