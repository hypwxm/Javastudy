package com.example.pro.utils.redisson.balancer;

/* 
    *集群调度策略
    * loadBalancer（负载均衡算法类的选择） 默认值：
    * org.redisson.connection.balancer.RoundRobinLoadBalancer
    * 
    * 在多Redis服务节点的环境里，可以选用以下几种负载均衡方式选择一个节点：
    * org.redisson.connection.balancer.WeightedRoundRobinBalancer - 权重轮询调度算法
    * org.redisson.connection.balancer.RoundRobinLoadBalancer - 轮询调度算法
    * org.redisson.connection.balancer.RandomLoadBalancer - 随机调度算法
*/

import org.redisson.connection.balancer.LoadBalancer;

public abstract class BalancerInter {
    public abstract LoadBalancer balancerWay();
}