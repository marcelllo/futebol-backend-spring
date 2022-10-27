package com.marcelo.futebol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GroupDTO {
    private String name;
    private PlayerDTO player;

    @JsonProperty("monthly_fee")
    private double monthlyFee;

    @JsonProperty("field_type")
    private String fieldType;
}
