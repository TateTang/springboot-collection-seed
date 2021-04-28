package com.company.project.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Author tangmf
 * @Date 2020/4/21 11:19
 * @Description Druid配置类
 */
@Configuration
public class DruidConfig {

    /**
     * 配置读取spring数据源
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 配置Druid监控启动页面
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> druidStartViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(),
                "/druid/*");
        // 白名单
        bean.addInitParameter("allow", "127.0.0.1");
        // IP黑名单 (存在共同时，deny优先于allow) :
        // 如果满足deny的话提示:Sorry, you are not permitted to view this page
        bean.addInitParameter("deny", "192.168.1.73");
        // 登录查看信息的账密码
        bean.addInitParameter("loginUsername", "admin");
        bean.addInitParameter("loginPassword", "admin");
        // 是否能够重置数据
        bean.addInitParameter("resetEnable", "false");
        return bean;
    }

    /**
     * Druid监控过滤器配置规则
     */
    @Bean
    public FilterRegistrationBean<WebStatFilter> druidStartFilter() {
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>(new WebStatFilter());
        // 添加过滤规则
        bean.addUrlPatterns("/*");
        // 添加不需要忽略的格式信息
        bean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return bean;
    }
}
