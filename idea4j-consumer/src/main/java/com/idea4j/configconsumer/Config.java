package com.idea4j.configconsumer;

import com.idea4j.common.shardb.DubboRequestFilter;
import com.idea4j.filter.TokenFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * Created by markee on 2017/1/4.
 */
@Configuration
@ComponentScan("com.idea4j")
public class Config {

    /*FilterRegistrationBean 用来配置urlpattern 来确定哪些路径触发filter */
    @Bean
    public FilterRegistrationBean someFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(TokenFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }

    /*使用annotation tag来取代<bean></bean>*/
    @Bean()
    public Filter TokenFilter() {
        return new TokenFilter();
    }
}
