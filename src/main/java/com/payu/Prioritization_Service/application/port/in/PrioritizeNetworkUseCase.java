package com.payu.Prioritization_Service.application.port.in;

import com.payu.Prioritization_Service.domain.model.PrioritizationRequest;

public interface PrioritizeNetworkUseCase {
    int[] prioritizeNetwork(PrioritizationRequest request);
}