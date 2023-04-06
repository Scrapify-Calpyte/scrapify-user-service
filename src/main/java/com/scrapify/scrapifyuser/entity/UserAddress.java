package com.scrapify.scrapifyuser.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.security.PrivateKey;

@Entity
@Data
@Table(name = "user_address")
@Where(clause = "is_deleted= false")

public class UserAddress extends AuditableBase {

    private Double latitude;
    private Double longitude;
    private String doorNo;
    private String state;
    private String landmark;


    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;
}
