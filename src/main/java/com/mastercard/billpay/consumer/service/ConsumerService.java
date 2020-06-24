package com.mastercard.billpay.consumer.service;

import com.mastercard.billpay.consumer.dto.ConsumerDto;
import com.mastercard.billpay.consumer.exception.ServiceException;

/**
 * Interface defining actions supported for Consumer service as per business logic
 *
 * @author Rahul Jadhao
 * @since 1.0
 */
public interface ConsumerService {

  /**
   * save consumer details.
   *
   * @param dto the consumer details
   * @throws ServiceException in case of error comes
   */
  String signUpConsumer(ConsumerDto dto) throws ServiceException;
}
