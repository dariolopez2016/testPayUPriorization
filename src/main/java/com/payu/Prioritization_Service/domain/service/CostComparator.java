package com.payu.Prioritization_Service.domain.service;

import com.payu.Prioritization_Service.domain.model.Network;

import java.util.Comparator;

public class CostComparator implements Comparator<Network> {
    @Override
    public int compare(Network a, Network b) {
        int cmp = Integer.compare(a.getCost(), b.getCost());
        return cmp != 0 ? cmp : Integer.compare(a.getResponseTime(), b.getResponseTime());
    }
}