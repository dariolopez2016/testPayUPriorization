package com.payu.Prioritization_Service.infrastructure.config;

import com.payu.Prioritization_Service.application.port.in.PrioritizeNetworkUseCase;
import com.payu.Prioritization_Service.application.port.out.NetworkDataPort;
import com.payu.Prioritization_Service.application.service.NetworkPrioritizationServiceImpl;
import com.payu.Prioritization_Service.infrastructure.adapter.RestNetworkDataAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PrioritizationConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public NetworkDataPort networkDataPort(RestTemplate restTemplate) {
        return new RestNetworkDataAdapter(restTemplate);
    }

    @Bean
    public PrioritizeNetworkUseCase prioritizeNetworkUseCase() {
        return new NetworkPrioritizationServiceImpl();
    }
}
