package com.scrapify.scrapifyuser.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "user_address")
@Where(clause = "is_deleted= false")
public class UserAddress extends IdentifiableBase {

    private Double latitude;
    private Double longitude;
    private String address;
    private String state;
    private String city;
    private String postalCode;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;


    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "user_id", nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;
}
