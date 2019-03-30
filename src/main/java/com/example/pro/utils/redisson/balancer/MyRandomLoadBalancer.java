package com.example.pro.utils.redisson.balancer;

import org.redisson.connection.balancer.LoadBalancer;
import org.redisson.connection.balancer.RandomLoadBalancer;

public class MyRandomLoadBalancer extends BalancerInter {

    @Override
    public LoadBalancer balancerWay() {
        return new RandomLoadBalancer();
    }
}