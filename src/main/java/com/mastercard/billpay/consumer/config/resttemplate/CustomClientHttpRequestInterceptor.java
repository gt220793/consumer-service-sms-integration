package com.mastercard.billpay.consumer.config.resttemplate;

import java.io.IOException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

/**
 * Interceptor to log incoming requests
 *
 * @author Mukhtar Sayyed
 */
@Component
public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

  @Override
  public ClientHttpResponse intercept(
      HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    return execution.execute(request, body);
  }
}
