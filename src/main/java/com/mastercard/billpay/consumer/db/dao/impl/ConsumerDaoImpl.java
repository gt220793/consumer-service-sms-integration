package com.mastercard.billpay.consumer.db.dao.impl;

import com.mastercard.billpay.consumer.db.dao.ConsumerDao;
import com.mastercard.billpay.consumer.db.entity.Consumer;
import com.mastercard.billpay.consumer.db.repository.ConsumerRepository;
import com.mastercard.billpay.consumer.dto.ConsumerDto;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementation of Consumer Profile Dao Impl. This implementation interacts with the Repository
 * and handle database errors
 *
 * @author Rahul Jadhao
 * @since 1.0
 */
@Component
@Slf4j
public class ConsumerDaoImpl implements ConsumerDao {

  @Autowired private ConsumerRepository consumerRepository;

  @Override
  public String signUpConsumer(ConsumerDto dto) {
    log.info("Save consumer::ConsumerDto: {}", dto);
    Consumer entity = Consumer.builder().build();
    transformDtoToEntity(dto, entity);
    ConsumerDto checkConsumer = getConsumerDetailsByEmailAddress(dto.getEmail());
    if (checkConsumer != null) {
      entity.setConsumerRefNumber(checkConsumer.getConsumerRefNumber());
    }
    entity = consumerRepository.save(entity);
    ConsumerDto saveConsumerdto = ConsumerDto.builder().build();
    transformEntityToDto(entity, saveConsumerdto);
    return saveConsumerdto.getNonce();
  }

  @Override
  public ConsumerDto getConsumerDetailsByEmailAddress(String emailAddress) {
    log.info("get Consumer Details By EmailAddress:: Consumer email address: {}", emailAddress);
    Optional<Consumer> consumerProfileEntity =
        consumerRepository.getConsumerDetailsByEmailAddress(emailAddress);
    ConsumerDto dto = null;
    if (!consumerProfileEntity.isEmpty()) {
      dto = ConsumerDto.builder().build();
      ConsumerDto finalDto = dto;
      consumerProfileEntity.ifPresent(entity -> transformEntityToDto(entity, finalDto));
    }
    return dto;
  }

  @Override
  public void transformDtoToEntity(ConsumerDto dto, Consumer entity) {
    entity.setFirstName(dto.getFirstName());
    entity.setLastName(dto.getLastName());
    entity.setEmail(dto.getEmail());
    entity.setMobileNumber(dto.getMobileNumber());
    entity.setSaltedHash(dto.getSaltedHash());
    entity.setNonce(dto.getNonce());
  }

  @Override
  public void transformEntityToDto(Consumer entity, ConsumerDto dto) {
    dto.setConsumerRefNumber(entity.getConsumerRefNumber());
    dto.setFirstName(entity.getFirstName());
    dto.setLastName(entity.getLastName());
    dto.setEmail(entity.getEmail());
    dto.setMobileNumber(entity.getMobileNumber());
    dto.setSaltedHash(entity.getSaltedHash());
    dto.setNonce(entity.getNonce());
  }
}
