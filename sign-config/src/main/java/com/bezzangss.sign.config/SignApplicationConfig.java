package com.bezzangss.sign.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@PropertySource(
        value = {
                "classpath:properties/application.properties",
                "classpath:properties/application-${spring.profiles.active}.properties",
                "classpath:properties/datasource/datasource.properties",
                "classpath:properties/datasource/datasource-${datasource.profile.active}.properties",
        },
        encoding = "UTF-8"
)
@ComponentScan(basePackages = "com.bezzangss")
@EnableScheduling
@EnableAspectJAutoProxy
@EnableTransactionManagement
@Configuration
public class SignApplicationConfig {
    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
