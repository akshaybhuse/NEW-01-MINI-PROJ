package com.akshay.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

//@Component
//@ConfigurationProperties(prefix = "plan-api")
//@Data

//@Configuration

//EnableConfigurationProperties
@Component
@ConfigurationProperties(prefix = "plan-api")
@Data
public class AppProperties {
    private Map<String,String> messages;
}
