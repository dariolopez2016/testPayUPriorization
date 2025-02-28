package com.payu.Prioritization_Service.application.service;

import com.payu.Prioritization_Service.application.port.in.PrioritizeNetworkUseCase;
import com.payu.Prioritization_Service.domain.model.Network;
import com.payu.Prioritization_Service.domain.model.PrioritizationRequest;
import com.payu.Prioritization_Service.domain.service.CostComparator;
import com.payu.Prioritization_Service.domain.service.ResponseTimeComparator;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class NetworkPrioritizationServiceImpl implements PrioritizeNetworkUseCase {
    private static final Map<String, Comparator<Network>> COMPARATORS = Map.of(
            "RESPONSE_TIME", new ResponseTimeComparator(),
            "COST", new CostComparator()
    );

    @Override
    public int[] prioritizeNetwork(PrioritizationRequest request) {
        List<Network> networks = new ArrayList<>();
        for (int i = 0; i < request.getResponseTimes().length; i++) {
            networks.add(Network.builder()
                    .index(i)
                    .responseTime(request.getResponseTimes()[i])
                    .cost(request.getCosts()[i])
                    .build());
        }

        Comparator<Network> comparator = COMPARATORS.getOrDefault(request.getCriteria(), new ResponseTimeComparator());
        networks.sort(comparator);

        return networks.stream().mapToInt(Network::getIndex).toArray();
    }
}
