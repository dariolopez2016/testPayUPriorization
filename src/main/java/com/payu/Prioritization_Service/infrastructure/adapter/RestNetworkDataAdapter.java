package com.payu.Prioritization_Service.infrastructure.adapter;

import com.payu.Prioritization_Service.application.port.out.NetworkDataPort;
import com.payu.Prioritization_Service.domain.model.NetworkData;
import org.springframework.web.client.RestTemplate;

public class RestNetworkDataAdapter implements NetworkDataPort {
    private final RestTemplate restTemplate;

    public RestNetworkDataAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public int[] getResponseTimes() {
        NetworkData data = restTemplate.getForObject("http://localhost:8081/api/networks/data", NetworkData.class);
        return data.getResponseTimes();
    }

    @Override
    public int[] getCosts() {
        NetworkData data = restTemplate.getForObject("http://localhost:8081/api/networks/data", NetworkData.class);
        return data.getCosts();
    }
}