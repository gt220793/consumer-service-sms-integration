package com.mastercard.billpay.consumer.config.rest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercard.billpay.consumer.utils.BillPayApiResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Class for handling controller level exception handling
 *
 * @author Mukhtar Sayyed
 * @since 1.0
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  ObjectMapper objectMapper;

  public RestExceptionHandler(Jackson2ObjectMapperBuilder mapperBuilder) {
    super();
    this.objectMapper = mapperBuilder.build();
  }

  /**
   * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter
   * is missing.
   *
   * @param ex MissingServletRequestParameterException
   * @param headers HttpHeaders
   * @param status HttpStatus
   * @param request WebRequest
   * @return the ErrorResponse object
   */
  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    String error = ex.getParameterName() + " parameter is missing";
    return new ResponseEntity<>(error, BAD_REQUEST);
  }

  /**
   * Handles uncaught exceptions and logs them
   *
   * @param ex the Exception
   * @return the ErrorResponse object
   */
  @ExceptionHandler(Exception.class)
  protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    String requestDetails = getRequestDetails(request);
    log.error("Unhandled exception at {}. Reason: {}", requestDetails, ex.getMessage(), ex);
    BillPayApiResponse<Object> response = BillPayApiResponse.builder().build();
    response.setMessage(ex.getMessage());
    response.setMessageCode(400);
    return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
  }

  /**
   * returns request details
   *
   * @param request
   * @return requestDetails
   */
  private String getRequestDetails(WebRequest request) {
    ServletWebRequest servletWebRequest = (ServletWebRequest) request;
    return servletWebRequest.getRequest().getRequestURI();
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    List<String> details = new ArrayList<>();
    for (ObjectError error : ex.getBindingResult().getAllErrors()) {
      details.add(error.getDefaultMessage());
    }
    BillPayApiResponse<Object> response = BillPayApiResponse.builder().build();
    response.setMessage(details.toString());
    response.setMessageCode(HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
  }
}
