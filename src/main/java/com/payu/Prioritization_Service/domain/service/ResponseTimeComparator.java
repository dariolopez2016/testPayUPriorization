package com.payu.Prioritization_Service.domain.service;

import com.payu.Prioritization_Service.domain.model.Network;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
@Slf4j
public class ResponseTimeComparator implements Comparator<Network> {
    @Override
    public int compare(Network a, Network b) {
        int cmp = Integer.compare(a.getResponseTime(), b.getResponseTime());
        if (cmp == 0) {
            log.info("Desempate: {}({}) vs {}({})",
                    a.getIndex(), a.getCost(), b.getIndex(), b.getCost());
        }
        return cmp != 0 ? cmp : Integer.compare(a.getCost(), b.getCost());
    }
}