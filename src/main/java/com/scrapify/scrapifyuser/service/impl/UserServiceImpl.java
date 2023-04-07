package com.scrapify.scrapifyuser.service.impl;


import com.scrapify.scrapifyuser.dto.pagination.PaginationDTO;
import com.scrapify.scrapifyuser.dto.pagination.SearchCriteria;
import com.scrapify.scrapifyuser.dto.pagination.TableResponse;
import com.scrapify.scrapifyuser.entity.User;
import com.scrapify.scrapifyuser.repo.UserRepository;
import com.scrapify.scrapifyuser.service.UserService;
import com.scrapify.scrapifyuser.specification.UserSpecification;
import com.scrapify.scrapifyuser.userexception.CustomException;
import com.scrapify.scrapifyuser.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

        @Autowired
        private UserRepository userRepository;

        private List<SearchCriteria> params = new ArrayList<>();


        @Override
        public User saveUser(User user) throws CustomException {
      //  validate(user);
        Mapper.setAuditable(user);
        userRepository.save(user);
        return user;
    }

        @Override
        public List<User> getAllUsers() {
        List<User> usersList = userRepository.findAll();
        return usersList.stream().map(user -> Mapper.map(user, User.class)).collect(Collectors.toList());
    }

        @Override
        public User findById(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = new User();
            user.setId(userOptional.get().getId());
            user.setFirstName(userOptional.get().getFirstName());
            return user;
        }
        return null;
    }

        @Override
        public List<User> findAllById(List<String> ids) {
        return userRepository.findAllById(ids);
    }


        @Override
        public void delete(String id) throws CustomException {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            //user.setIsDeleted(true);
            userRepository.save(user);
        }

    }

//        private void validate(User user) throws CustomException {
//        User userExist = userRepository.findByName(user.getName());
//        if (userExist != null && (!userExist.getId().equals(user.getId()))) {
//            throw new CustomException("Duplicate User name");
//        }
//    }

        @Override
        public User findByUser(String user) {
//        User eUser = userRepository.findByName(user);
//        if(eUser != null){
//            return eUser;
//        }
        return null;
    }

    @Override
    public TableResponse getUsers(PaginationDTO pagination) {
        TableResponse response;
        Pageable paging = PageRequest.of(pagination.getPageNo() - 1, pagination.getPageSize());
        params.clear();
        Page<User> userPage = userRepository.findAll(getSpecifications(pagination),paging);
        if (userPage.hasContent()) {
            List<User> userList = userPage.getContent();
            response = new TableResponse(0, (int) userPage.getTotalElements(), (int) userPage.getTotalElements(),
                    userList);
        } else {
            response = new TableResponse(0, (int) userPage.getTotalElements(), (int) userPage.getTotalElements(),
                    new ArrayList<>());
        }
        return response;
    }

    private Specification<User> getSpecifications(PaginationDTO pagination) {
        pagination.getFilter().forEach(searchCriteria -> {
            params.add(searchCriteria);
        });

        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(UserSpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i)
                    .isOrPredicate()
                    ? Specification.where(result)
                    .or(specs.get(i))
                    : Specification.where(result)
                    .and(specs.get(i));
        }

        return result;
    }


}
