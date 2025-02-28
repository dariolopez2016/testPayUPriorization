package com.payu.Prioritization_Service.infrastructure.controller;

import com.payu.Prioritization_Service.application.port.in.PrioritizeNetworkUseCase;
import com.payu.Prioritization_Service.application.port.out.NetworkDataPort;
import com.payu.Prioritization_Service.domain.model.PrioritizationRequest;
import com.payu.Prioritization_Service.infrastructure.controller.dto.PrioritizationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prioritization")
@Tag(name = "Prioritization", description = "APIs para priorización de redes") // Define la categoría en Swagger
public class PrioritizationController {
    private final PrioritizeNetworkUseCase useCase;
    private final NetworkDataPort dataPort;

    public PrioritizationController(PrioritizeNetworkUseCase useCase, NetworkDataPort dataPort) {
        this.useCase = useCase;
        this.dataPort = dataPort;
    }

    @PostMapping("/prioritize")
    @Operation(summary = "Prioriza las redes", description = "Recibe tiempos de respuesta y costos para priorizar redes.")
    public PrioritizationResponse prioritizeNetworks(@RequestBody PrioritizationRequest request) {
        return new PrioritizationResponse(useCase.prioritizeNetwork(request));
    }

    @GetMapping("/prioritize-example")
    public PrioritizationResponse prioritizeExample() {
        PrioritizationRequest request = new PrioritizationRequest(
                dataPort.getResponseTimes(),
                dataPort.getCosts(),
                "RESPONSE_TIME"
        );
        return new PrioritizationResponse(useCase.prioritizeNetwork(request));
    }
}
