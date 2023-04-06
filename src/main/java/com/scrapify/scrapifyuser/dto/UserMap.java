package com.scrapify.scrapifyuser.dto;

import lombok.Data;

@Data
public class UserMap {
    private String id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Double distance;
}
