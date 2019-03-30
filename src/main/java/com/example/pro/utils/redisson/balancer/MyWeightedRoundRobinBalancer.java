package com.example.pro.utils.redisson.balancer;

import java.util.HashMap;

import org.redisson.connection.balancer.LoadBalancer;
import org.redisson.connection.balancer.WeightedRoundRobinBalancer;

public class MyWeightedRoundRobinBalancer extends BalancerInter {
    private final int _defaultWeight = 10;
    private int defaultWeight;
    private HashMap<String, Integer> weight = null;

    public MyWeightedRoundRobinBalancer(int defaultWeight) {
        this.defaultWeight = defaultWeight;
    }

    public MyWeightedRoundRobinBalancer(HashMap<String, Integer> weight) {
        this.weight = weight;
        this.defaultWeight = _defaultWeight;
    }

    public MyWeightedRoundRobinBalancer(HashMap<String, Integer> weight, int defaultWeight) {
        this.weight = weight;
        this.defaultWeight = defaultWeight;
    }

    @Override
    public LoadBalancer balancerWay() {
        return new WeightedRoundRobinBalancer(this.weight, this.defaultWeight);
    }
}