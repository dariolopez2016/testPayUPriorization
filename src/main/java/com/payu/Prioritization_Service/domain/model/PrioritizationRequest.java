package com.payu.Prioritization_Service.domain.model;

public class PrioritizationRequest {
    private int[] responseTimes;
    private int[] costs;
    private String criteria;

    public PrioritizationRequest(int[] responseTimes, int[] costs, String criteria) {
        this.responseTimes = responseTimes;
        this.costs = costs;
        this.criteria = criteria;
    }

    public int[] getResponseTimes() { return responseTimes; }
    public int[] getCosts() { return costs; }
    public String getCriteria() { return criteria; }
}