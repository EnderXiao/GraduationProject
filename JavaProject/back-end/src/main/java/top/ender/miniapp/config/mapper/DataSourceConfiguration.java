package top.ender.miniapp.config.mapper;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;


/**
 * @author Ender-PC
 * @date 2021/2/9
 * mybatis配置数据源
 */
@Configuration
// 配置mybatis mapper扫描路径
@MapperScan("top.ender.miniapp.dao")
public class DataSourceConfiguration {
    @Value("${jdbc.driver}")
    private String jdbcDriver;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Bean(name = "dataSource")
    public ComboPooledDataSource creatDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(jdbcDriver);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
//        // 配置c3p0连接池的私有属性
//        // 连接池最大线程数
//        dataSource.setMaxPoolSize(30);
//        // 连接池最小线程数
//        dataSource.setMinPoolSize(10);
//        // 关闭连接后不自动commit
//        dataSource.setAutoCommitOnClose(false);
//        // 连接超时时间
//        dataSource.setCheckoutTimeout(10000);
//        // 连接失败重试次数
//        dataSource.setAcquireRetryAttempts(2);
        return dataSource;
    }
}
