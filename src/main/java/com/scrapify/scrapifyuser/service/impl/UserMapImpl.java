package com.scrapify.scrapifyuser.service.impl;

import com.scrapify.scrapifyuser.dto.UserMap;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapImpl implements RowMapper<UserMap> {

    @Override
    public UserMap mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserMap user = new UserMap();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("first_name"));
        user.setLatitude(rs.getDouble("latitude"));
        user.setLongitude(rs.getDouble("longitude"));
        user.setDistance(rs.getDouble("distance"));
        return user;
    }
}
