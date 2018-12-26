package cn.instructorsystem.instructor.config.dao;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
// 配置mybatis mapper的扫面路径
@MapperScan("cn.instructorsystem.student.dao")
public class DruidDBConfig {
    private Logger logger = LoggerFactory.getLogger(DruidDBConfig.class);
    @Value("${jdbc_driverClass}")
    private String jdbcDriverClass;
    @Value("${jdbc_url}")
    private String jdbcUrl;
    @Value("${jdbc_user}")
    private String jdbcUser;
    @Value("${jdbc_pwd}")
    private String jdbcPwd;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(jdbcDriverClass);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUser);
        dataSource.setPassword(jdbcPwd);
        return dataSource;
    }
}
