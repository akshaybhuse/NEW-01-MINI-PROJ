package com.akshay.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

//@Configuration
//EnableConfigurationProperties
//@ConfigurationProperties(prefix = "plan-api")
//@Data




@Component
@ConfigurationProperties(prefix = "plan-api")
@Data
public class AppProperties {
    private Map<String,String> messages;
}
