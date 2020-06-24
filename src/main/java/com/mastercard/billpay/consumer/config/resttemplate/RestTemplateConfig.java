package com.mastercard.billpay.consumer.config.resttemplate;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration for RestTemplate
 *
 * @author Mukhtar Sayyed
 */
@Configuration
public class RestTemplateConfig {

  @Bean
  public RestTemplateBuilder restTemplateBuilder(
      @Qualifier("customRestTemplateCustomizer")
          CustomRestTemplateCustomizer customRestTemplateCustomizer,
      @Qualifier("customErrorResponseHandler")
          CustomErrorResponseHandler customErrorResponseHandler) {
    return new RestTemplateBuilder(customRestTemplateCustomizer)
        .setConnectTimeout(Duration.ofMinutes(1l))
        .setReadTimeout(Duration.ofMinutes(1l))
        .errorHandler(customErrorResponseHandler);
  }

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder.build();
  }
}
