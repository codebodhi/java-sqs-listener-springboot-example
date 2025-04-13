package com.codebodhi.sqslib.springboot.config;

import com.codebodhi.sqslistener.SqsListenerConfig;
import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqsListenerConfiguration {
  @Bean("primeFactorReqListenerConfig")
  public SqsListenerConfig primeFactorReqListenerConfig() {
    return SqsListenerConfig.builder()
        .parallelism(5)
        .pollingFrequency(Duration.ofSeconds(5))
        .visibilityTimeout(Duration.ofSeconds(60))
        .build();
  }
}
