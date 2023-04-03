package com.scrapify.scrapifyuser.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
@Data
public class IdentifiableBase {
	   @Id
	    @GeneratedValue(generator = "custom-generator",
	            strategy = GenerationType.IDENTITY)
	    @GenericGenerator(
	            name = "custom-generator",
	            strategy = "com.scrapify.scrapifyuser.entity.BaseIdentifierGenerator")
	    protected String id;
}
