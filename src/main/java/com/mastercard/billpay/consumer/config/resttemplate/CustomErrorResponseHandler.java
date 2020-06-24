package com.mastercard.billpay.consumer.config.resttemplate;

import java.io.IOException;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * Custom ResponseErrorHandler for handling http response with server error
 *
 * @author Mukhtar Sayyed
 */
@Component
@Slf4j
public class CustomErrorResponseHandler implements ResponseErrorHandler {

  @Override
  public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
    return (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
        || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
  }

  @Override
  public void handleError(URI url, HttpMethod method, ClientHttpResponse response)
      throws IOException {
    HttpStatus status = HttpStatus.NOT_FOUND;
    String body = null;
    if (response != null) {
      status = response.getStatusCode();
    } else {
      log.error("HttpResponse is null !!! please check if server is reachable");
    }
    // throw new ExternalServerException(CustomErrorResponseHandler.class, status, url, method);
  }

  @Override
  public void handleError(ClientHttpResponse httpResponse) throws IOException { //
    // error handling already done
  }
}
