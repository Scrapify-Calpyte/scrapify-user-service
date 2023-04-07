package com.scrapify.scrapifyuser.entity;



import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "users")
@Where(clause = "is_deleted = false")
public class User extends IdentifiableBase {

    private String id;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String userType;

    @Column(columnDefinition = "boolean default true")
    private Boolean isActive;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    private String address;
    private String gender;
    private String profileImage;

}
