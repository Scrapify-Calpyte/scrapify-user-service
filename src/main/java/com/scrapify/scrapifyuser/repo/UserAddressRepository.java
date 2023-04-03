package com.scrapify.scrapifyuser.repo;


import com.scrapify.scrapifyuser.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, String>, JpaSpecificationExecutor<UserAddress> {

    UserAddress findByLatitudeAndLongitude(Double latitude, Double longitude);
}
