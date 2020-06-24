package com.mastercard.billpay.consumer.db.dao;

import com.mastercard.billpay.consumer.db.entity.Consumer;
import com.mastercard.billpay.consumer.dto.ConsumerDto;

/**
 * Dao layer for managing consumer entity. The entity and database exceptions need to be handled
 * here. The Dao layer can only exchange DTO or Custom Dao exceptions with the outer packages
 *
 * @author Rahul Jadhao
 * @since 1.0
 */
public interface ConsumerDao extends BaseDao<ConsumerDto, Consumer> {

  /**
   * save consumer details.
   *
   * @param dto the consumer details
   */
  String signUpConsumer(ConsumerDto dto);

  /**
   * get the consumer details on bases of emailAddress.
   *
   * @param emailAddress consumer emailAddress.
   */
  ConsumerDto getConsumerDetailsByEmailAddress(String emailAddress);
}
