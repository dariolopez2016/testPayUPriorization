package com.payu.Prioritization_Service.infrastructure.controller;


import com.payu.Prioritization_Service.application.port.in.PrioritizeNetworkUseCase;
import com.payu.Prioritization_Service.application.port.out.NetworkDataPort;
import com.payu.Prioritization_Service.domain.model.PrioritizationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class PrioritizationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PrioritizeNetworkUseCase useCase;

    @Mock
    private NetworkDataPort dataPort;

    @InjectMocks
    private PrioritizationController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testPrioritizeNetworks_Success() throws Exception {
        // Datos de prueba
        PrioritizationRequest request = new PrioritizationRequest(
                new int[]{20, 15, 100, 15, 50, 9},
                new int[]{50, 60,  30, 55, 40, 70},
                "RESPONSE_TIME"
        );
        int[] prioritizedIndexes = new int[]{1, 0, 2};

        // Configura el mock para que coincida con cualquier PrioritizationRequest con los mismos valores
        when(useCase.prioritizeNetwork(argThat(req ->
                Arrays.equals(req.getResponseTimes(), request.getResponseTimes()) &&
                        Arrays.equals(req.getCosts(), request.getCosts()) &&
                        req.getCriteria().equals(request.getCriteria())
        ))).thenReturn(prioritizedIndexes);

        // solicitud POST con JSON
        String jsonRequest = "{\"responseTimes\":[20, 15, 100, 15, 50, 9],\"costs\":[50, 60,  30, 55, 40, 70],\"criteria\":\"RESPONSE_TIME\"}";

        // Ejecuta y verifica
        mockMvc.perform(post("/api/prioritization/prioritize")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print()) // Para depurar
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prioritizedIndexes[0]").value(1))
                .andExpect(jsonPath("$.prioritizedIndexes[1]").value(0))
                .andExpect(jsonPath("$.prioritizedIndexes[2]").value(2));

        verify(useCase, times(1)).prioritizeNetwork(any(PrioritizationRequest.class));
    }

    @Test
    void testPrioritizeExample_Success() throws Exception {
        // Datos de entrada
        int[] responseTimes = new int[]{20, 15, 100, 15, 50, 9};
        int[] costs = new int[]{50, 60, 30, 55, 40, 70};
        int[] prioritizedIndexes = new int[]{5, 3, 1, 0, 4, 2}; // Resultado esperado

        // Configura los mocks
        when(dataPort.getResponseTimes()).thenReturn(responseTimes);
        when(dataPort.getCosts()).thenReturn(costs);
        when(useCase.prioritizeNetwork(any(PrioritizationRequest.class))).thenReturn(prioritizedIndexes);

        // Ejecuta la solicitud GET y verifica
        mockMvc.perform(get("/api/prioritization/prioritize-example"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prioritizedIndexes[0]").value(5))
                .andExpect(jsonPath("$.prioritizedIndexes[1]").value(3))
                .andExpect(jsonPath("$.prioritizedIndexes[2]").value(1))
                .andExpect(jsonPath("$.prioritizedIndexes[3]").value(0))
                .andExpect(jsonPath("$.prioritizedIndexes[4]").value(4))
                .andExpect(jsonPath("$.prioritizedIndexes[5]").value(2));

        // Verifica las interacciones
        verify(dataPort, times(1)).getResponseTimes();
        verify(dataPort, times(1)).getCosts();
        verify(useCase, times(1)).prioritizeNetwork(any(PrioritizationRequest.class));
    }
}
