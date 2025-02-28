package com.payu.Prioritization_Service.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Network {
    private int index;
    private int responseTime;
    private int cost;

}