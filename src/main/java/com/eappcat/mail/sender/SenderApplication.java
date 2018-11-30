package com.eappcat.mail.sender;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SenderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SenderApplication.class, args);
    }


    @Bean
    @ConditionalOnMissingBean(VelocityEngine.class)
    VelocityEngine velocityEngine() {
        VelocityEngine engine = new VelocityEngine();
        engine.addProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
        engine.addProperty("input.encoding", "UTF-8");
        engine.addProperty("output.encoding", "UTF-8");
        return engine;
    }
}
