package com.example.pro.utils.redisson;

import java.io.File;

import com.example.pro.utils.redisson.balancer.BalancerInter;
import com.example.pro.utils.redisson.balancer.MyRoundRobinLoadBalancer;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.config.SubscriptionMode;
import org.redisson.config.TransportMode;
import org.springframework.core.io.ClassPathResource;

public class RedissonConfig {
    /// https://github.com/redisson/redisson/wiki/2.-%E9%85%8D%E7%BD%AE%E6%96%B9%E6%B3%95
    // Redisson程序化的配置方法是通过构建Config对象实例来实现的。例如：
    /*
     * 
     * @param: bi 传入一个轮训策略，为null为调整为默认策略
     * 
     */
    public RedissonConfig(BalancerInter bi) {

        if (bi == null) {
            bi = new MyRoundRobinLoadBalancer();
        }

        Config config = new Config();
        {
            // 默认值: 当前处理核数量 * 2
            // 这个线程池数量被所有RTopic对象监听器，RRemoteService调用者和RExecutorService任务共同共享。
            config.setThreads(2);
        }
        {
            // 默认值: 当前处理核数量 * 2
            // 这个线程池数量是在一个Redisson实例内，被其创建的所有分布式数据类型和服务，以及底层客户端所一同共享的线程池里保存的线程数量。
            config.setNettyThreads(2);
        }

        /*
         * ExecutorService exeS =
         * 
         * config.setExecutor();
         */
        {
            // transportMode（传输模式）
            // 默认值：TransportMode.NIO

            // 可选参数：
            // TransportMode.NIO,
            // TransportMode.EPOLL - 需要依赖里有netty-transport-native-epoll包（Linux）
            // TransportMode.KQUEUE - 需要依赖里有 netty-transport-native-kqueue包（macOS）
            config.setTransportMode(TransportMode.EPOLL);
        }
        {
            // lockWatchdogTimeout（监控锁的看门狗超时，单位：毫秒）
            // 默认值：30000
            // 监控锁的看门狗超时时间单位为毫秒。该参数只适用于分布式锁的加锁请求中未明确使用leaseTimeout参数的情况。如果该看门口未使用lockWatchdogTimeout去重新调整一个分布式锁的lockWatchdogTimeout超时，那么这个锁将变为失效状态。这个参数可以用来避免由Redisson客户端节点宕机或其他原因造成死锁的情况。
            config.setLockWatchdogTimeout(30000);
        }
        {
            /*
             * keepPubSubOrder（保持订阅发布顺序） 默认值：true
             * 通过该参数来修改是否按订阅发布消息的接收顺序出来消息，如果选否将对消息实行并行处理，该参数只适用于订阅发布消息的情况。
             */

            // 获取当前是否为保持订阅发布顺序模式
            Boolean isKeepPub = config.isKeepPubSubOrder();
            config.setKeepPubSubOrder(false);
        }

        {

            /*
             * @FUNCTION setReadMode
             *
             * readMode（读取操作的负载均衡模式） 默认值： SLAVE（只在从服务节点里读取）
             * 注：在从服务节点里读取的数据说明已经至少有两个节点保存了该数据，确保了数据的高可用性。 设置读取操作选择节点的模式。 可用值为： SLAVE -
             * 只在从服务节点里读取。 MASTER - 只在主服务节点里读取。 MASTER_SLAVE - 在主从服务节点里都可以读取。
             */

            /*
             * subscriptionMode（订阅操作的负载均衡模式） 默认值：SLAVE（只在从服务节点里订阅）
             * 
             * 设置订阅操作选择节点的模式。 可用值为： SLAVE - 只在从服务节点里订阅。 MASTER - 只在主服务节点里订阅。
             */
            /*
             * loadBalancer（负载均衡算法类的选择） 默认值：
             * org.redisson.connection.balancer.RoundRobinLoadBalancer
             * 
             * 在多Redis服务节点的环境里，可以选用以下几种负载均衡方式选择一个节点：
             * org.redisson.connection.balancer.WeightedRoundRobinBalancer - 权重轮询调度算法
             * org.redisson.connection.balancer.RoundRobinLoadBalancer - 轮询调度算法
             * org.redisson.connection.balancer.RandomLoadBalancer - 随机调度算法
             */
        }
        // 可以用"rediss://"来启用SSL连接
        config.useClusterServers().setScanInterval(2000) // 集群状态扫描间隔时间，单位是毫秒
                .setSubscriptionMode(SubscriptionMode.MASTER) //
                .setReadMode(ReadMode.SLAVE) //
                .setLoadBalancer(bi.balancerWay()) //
                .addNodeAddress("redis://127.0.0.1:6379");

        Redisson.create(config);
    }

    // 2.2.1 通过JSON或YAML格式配置
    // Redisson的配置文件可以是JSON格式或YAML格式。
    // 可以通过调用Config.fromJSON方法并指定一个File实例来实现读取JSON格式的配置：
    public RedissonConfig(String filename) {
        try {
            // filename为json文件
            Config config = Config.fromJSON(new File(filename));
            // Config config = Config.fromYAML(new File("config-file.yaml"));
            RedissonClient redisson = Redisson.create(config);
            // 调用Config.toJSON方法可以将一个Config配置实例序列化为一个含有JSON数据类型的字符串：
            String jsonFormat = config.toJSON();
            // 也通过调用config.fromYAML方法并指定一个File实例来实现读取YAML格式的配置：
            String yamlFormat = config.toYAML();
        } catch (Exception e) {

        }
    }

    public static RedissonClient ymlConfig(String filename) {
        try {
            Config config = Config.fromYAML(new ClassPathResource(filename).getInputStream());
            RedissonClient client = Redisson.create(config);
            return client;
        } catch (Exception e) {
            // redis初始化失败，直接使程序奔溃
            System.exit(1);
        }
        return null;
    }
}