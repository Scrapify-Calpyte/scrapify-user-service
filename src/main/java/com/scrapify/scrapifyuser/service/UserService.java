package com.scrapify.scrapifyuser.service;

import com.scrapify.scrapifyuser.dto.pagination.PaginationDTO;
import com.scrapify.scrapifyuser.dto.pagination.TableResponse;
import com.scrapify.scrapifyuser.entity.User;
import com.scrapify.scrapifyuser.userexception.CustomException;

import java.util.List;

public interface UserService {

        User saveUser(User user) throws CustomException;

        List<User> getAllUsers();

        User findById(String id);

        List<User> findAllById(List<String> ids);

        void delete(String id) throws CustomException;

        User findByUser(String user) ;

        TableResponse getUsers(PaginationDTO pagination);

}
