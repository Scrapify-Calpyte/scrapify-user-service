package com.scrapify.scrapifyuser.entity;



import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "app_user")
@Where(clause = "is_deleted = false")
public class User extends AuditableBase {

    private String id;
    private String name;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String userType;

    @Column(columnDefinition = "boolean default true")
    private Boolean isActive;

    private String password;

}
