package com.news.common.init;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.jackson.ConfusionModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitJacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper = ConfusionModule.registerModule(objectMapper);
        return objectMapper;
    }

}
