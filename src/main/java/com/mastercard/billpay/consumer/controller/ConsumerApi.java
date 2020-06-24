package com.mastercard.billpay.consumer.controller;

import com.mastercard.billpay.consumer.dto.ConsumerDto;
import com.mastercard.billpay.consumer.exception.ServiceException;
import com.mastercard.billpay.consumer.service.ConsumerService;
import com.mastercard.billpay.consumer.utils.BillPayApiResponse;
import java.util.Collections;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest interface for Address API's. This interacts with the consumer service
 *
 * @author Rahul Jadhao
 * @since 1.0
 */
@RestController
@RequestMapping("/v1/consumer")
@Slf4j
public class ConsumerApi extends BaseApi {

  @Autowired private ConsumerService consumerService;

  @PostMapping("/signUp")
  public ResponseEntity<BillPayApiResponse<String>> registerConsumer(
      @RequestBody @Valid ConsumerDto request) {
    try {
      return ResponseEntity.ok(
          new BillPayApiResponse<String>(
              200, "SUCC", Collections.singletonList(consumerService.signUpConsumer(request))));
    } catch (ServiceException e) {
      return ResponseEntity.ok(new BillPayApiResponse(400, e.getMessage(), null));
    }
  }
}
