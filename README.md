# JTT809 上级平台数据接入服务
    支持JTT809-2011
## 开发环境
### 环境
    Java JDK1.8 Mina
### 添加依赖
    target/lib Add as library
## 依赖服务
### Kafka
    docker run -d --restart=always --log-driver json-file --log-opt max-size=100m --log-opt max-file=2 --name kafka -p 9092:9092 -e KAFKA_BROKER_ID=0 -e KAFKA_ZOOKEEPER_CONNECT=172.16.0.13:2181/kafka -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://172.16.0.13:9092 -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092  wurstmeister/kafka
### redis
    docker run -itd --name redis-test -p 6379:6379 redis --requirepass 1qaz@WSX

## 业务
    1200
        1201：上传车辆注册信息，TOPIC：JT809-REGISTER-V1
        1202：事实上传车辆定位信息，TOPIC：JT809-POSITION-V1
        1203：车辆定位信息自动补报，TOPIC：JT809-POSITION-V1
    1400 主链路报警信息交互消息
        1402：上报报警信息
    
        
        
