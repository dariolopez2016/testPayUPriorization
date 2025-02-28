package com.payu.Prioritization_Service.infrastructure.controller.dto;

import lombok.Data;


public class PrioritizationResponse {
    private int[] prioritizedIndexes;

    public PrioritizationResponse(int[] prioritizedIndexes) {
        this.prioritizedIndexes = prioritizedIndexes;
    }

    public int[] getPrioritizedIndexes() {
        return prioritizedIndexes;
    }
}