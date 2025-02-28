package com.payu.Prioritization_Service.application.port.out;

public interface NetworkDataPort {
    int[] getResponseTimes();
    int[] getCosts();
}
