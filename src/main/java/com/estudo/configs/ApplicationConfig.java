package com.estudo.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS;

@Configuration
public class ApplicationConfig {

    public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder()
                .failOnUnknownProperties(false)
                .modules(
                        new Jdk8Module(),
                        new JavaTimeModule()
                )
                .dateFormat(new SimpleDateFormat(DATE_PATTERN))
                .featuresToDisable(
                        WRITE_DATES_AS_TIMESTAMPS,
                        WRITE_DATE_KEYS_AS_TIMESTAMPS
                )
                .build();

        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);

        return objectMapper;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
