package com.example.pro.utils.redisson.balancer;

import org.redisson.connection.balancer.LoadBalancer;
import org.redisson.connection.balancer.RoundRobinLoadBalancer;

public class MyRoundRobinLoadBalancer extends BalancerInter {
    @Override
    public LoadBalancer balancerWay() {
        return new RoundRobinLoadBalancer();
    }
}