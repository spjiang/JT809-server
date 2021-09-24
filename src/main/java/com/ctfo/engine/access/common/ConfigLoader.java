package com.ctfo.engine.access.common;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import com.ctfo.engine.access.common.cache.LocalCache;
import com.ctfo.protocol.P809.vo.PlatformAccessInfo;
import com.ctfo.vsme.engine.common.exception.ConfigLoadException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 配置文件加载处理类
 *
 * @author 凉意 2018年5月14日 上午10:02:55
 * @version 1.0
 */
@Slf4j
public class ConfigLoader {

    /**
     * 配置文件路径
     */
    private static String configXml = "";

    /**
     * 系统参数配置信息
     */
    public static final Map<String, String> CONFIG_SYS_PARAM = new HashMap<String, String>();

    /**
     * Kafka主题配置
     */
    public static final Map<String, String> CONFIG_KF_TOPIC = new HashMap<String, String>();

    /**
     * Redis缓存服务配置信息
     */
    public static final Map<String, String> CONFIG_REDISCONNECTION = new HashMap<>();

    /**
     * Kafka生产者配置信息
     */
    private static Properties configKafkaProuctor = null;

    /**
     * Kafka消费配置信息
     */
    private static Properties configKafkaConsumer = null;

    /**
     * Kafka鉴权配置信息
     */
    private static Properties configKafkaAuth = null;

    /**
     * 加载配置文件
     *
     * @param configXmlPath 配置文件路径
     * @throws ConfigLoadException
     * @author 凉意 2018年5月14日 下午1:34:59
     */
    public static void load(String configXmlPath) throws ConfigLoadException {
        try {
            if (StringUtils.isBlank(configXmlPath)) {
                configXml = System.getProperty("user.dir") + System.getProperty("file.separator") + "configs" + System.getProperty("file.separator") + "DataAccessService.xml";
            } else {
                configXml = configXmlPath;
            }
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new File(configXml));
            Element root = doc.getRootElement();

            log.info("开始加载配置文件...");
            // 加载系统参数配置
            CONFIG_SYS_PARAM.putAll(getNameMap(root.element("systemparam")));
            // 加载Redis缓存服务客户端配置
            CONFIG_REDISCONNECTION.putAll(getNameMap(root.element("RedisConnection")));
            // 加载Kafka主题配置
            CONFIG_KF_TOPIC.putAll(getNameMap(root.element("KFTopic")));
            ;
            // 加载Kafka生产者配置信息
            configKafkaProuctor = getProperties(root.element("KafkaConnection").element("Productor").elements("property"));
            // 加载Kafka消费配置信息
            configKafkaConsumer = getProperties(root.element("KafkaConnection").element("Consumer").elements("property"));
            // 加载Kafka鉴权配置信息
            configKafkaAuth = getProperties(root.element("KafkaConnection").element("Authenticate").elements("property"));
            if (Boolean.parseBoolean(ConfigLoader.getConfigKafkaAuth().getProperty("isAuth"))) {
                configKafkaProuctor.put("security.protocol", configKafkaAuth.get("security.protocol"));
                configKafkaProuctor.put("sasl.mechanism", configKafkaAuth.get("sasl.mechanism"));
                configKafkaProuctor.put("sasl.jaas.config", configKafkaAuth.get("productor.auth"));
                configKafkaConsumer.put("security.protocol", configKafkaAuth.get("security.protocol"));
                configKafkaConsumer.put("sasl.mechanism", configKafkaAuth.get("sasl.mechanism"));
                configKafkaConsumer.put("sasl.jaas.config", configKafkaAuth.get("consumer.auth"));
            }
            // 服务商平台配置
            Element sppElement = root.element("Platforms");
            List<?> spps = sppElement.elements("Platform");
            LocalCache.PLATFORM_CONFIG.clear();
            for (Object element : spps) {
                Element em = (Element) element;
                PlatformAccessInfo platform = new PlatformAccessInfo();
                platform.setUserId(Long.parseLong(em.element("userId").getTextTrim()));
                platform.setPassword(em.element("password").getTextTrim());
                platform.setGnnsCenterId(Long.parseLong(em.element("access").getTextTrim()));
                platform.setM1(Long.parseLong(em.element("M1").getTextTrim()));
                platform.setIA1(Long.parseLong(em.element("IA1").getTextTrim()));
                platform.setIC1(Long.parseLong(em.element("IC1").getTextTrim()));
                LocalCache.PLATFORM_CONFIG.put(String.valueOf(platform.getGnnsCenterId()), platform);
            }
            log.info("配置文件加载成功！");
        } catch (Exception e) {
            throw new ConfigLoadException("加载配置文件出现异常", e);
        }
    }

    /**
     * 初始化日志配置
     *
     * @throws ConfigLoadException
     * @author 凉意 2018年5月14日 下午1:36:27
     */
    public static void loadLogback() throws ConfigLoadException {
        String logbackFile = System.getProperty("user.dir") + System.getProperty("file.separator") + "configs" + System.getProperty("file.separator") + "logback.xml";
        log.info("loadLogback:" + logbackFile);
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        lc.reset();
        try {
            configurator.doConfigure(logbackFile);
        } catch (JoranException e) {
            throw new ConfigLoadException("初始化日志配置失败,文件路径：" + logbackFile, e);
        }
        StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
    }

    /**
     * 生成指定节点下的所有节点的name属性和值的映射表
     *
     * @param element 指定节点
     * @return name属性和值的映射表
     * @author 凉意 2018年5月14日 下午1:51:18
     */
    public static Map<String, String> getNameMap(Element element) {
        Map<String, String> map = new HashMap<String, String>();
        List<?> elements = element.elements("property");
        for (Object em : elements) {
            String key = ((Element) em).attributeValue("name");
            String value = ((Element) em).getTextTrim();
            map.put(key, value);
        }
        return map;
    }

    /**
     * 将XML格式的键值映射转换为Properties对象
     *
     * @param elements
     * @return
     * @author 凉意 2018年5月17日 下午2:21:29
     */
    public static Properties getProperties(List<?> elements) {
        Properties ppts = new Properties();
        for (Object em : elements) {
            String name = ((Element) em).attributeValue("name");
            String value = ((Element) em).getTextTrim();
            ppts.put(name, value);
        }
        return ppts;
    }

    public static Properties getConfigKafkaProuctor() {
        return configKafkaProuctor;
    }

    public static Properties getConfigKafkaConsumer() {
        return configKafkaConsumer;
    }

    public static Properties getConfigKafkaAuth() {
        return configKafkaAuth;
    }

}
