package com.scrapify.scrapifyuser.controller;

import com.scrapify.scrapifyuser.dto.UserMap;
import com.scrapify.scrapifyuser.service.impl.UserMapImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user/map")
public class UserMapController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/nearby-users")
    public List<UserMap> findNearbyUsers(@RequestParam("lat") double lat, @RequestParam("lng") double lng) {
        String query = "SELECT u.id, u.first_name , au.latitude, au.longitude , au.id as address_id \n" +
                "(6371 * acos(cos(radians(?)) * cos(radians(latitude)) * cos(radians(longitude) - radians(?)) + sin(radians(?)) * sin(radians(latitude)))) AS distance\n" +
                "from user_address au inner join users u on u.id = au.user_id;";
        List<UserMap> users = jdbcTemplate.query(query, new Object[]{lat, lng, lat}, new UserMapImpl());
        List<UserMap> userMaps = new ArrayList<>();
        users.stream().forEach(temp -> {
            if(temp.getDistance() <= 5.0){
                userMaps.add(temp);
            }
        });
        return userMaps;
    }
}
