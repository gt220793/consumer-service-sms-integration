package com.mastercard.billpay.consumer.config.resttemplate;

import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Customize controller template with an interceptor
 *
 * @author Mukhtar Sayyed
 */
@Component
public class CustomRestTemplateCustomizer implements RestTemplateCustomizer {

  @Autowired CustomClientHttpRequestInterceptor customClientHttpRequestInterceptor;

  @Override
  public void customize(RestTemplate restTemplate) {
    restTemplate.getInterceptors().add(customClientHttpRequestInterceptor);
    StringHttpMessageConverter stringHttpMessageConverter =
        new StringHttpMessageConverter(StandardCharsets.UTF_8);
    stringHttpMessageConverter.setWriteAcceptCharset(false);
    restTemplate.getMessageConverters().add(0, stringHttpMessageConverter);
  }
}
