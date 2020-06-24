package com.mastercard.billpay.consumer.service.impl;

import com.mastercard.billpay.consumer.db.dao.ConsumerDao;
import com.mastercard.billpay.consumer.dto.ConsumerDto;
import com.mastercard.billpay.consumer.exception.ServiceException;
import com.mastercard.billpay.consumer.service.ConsumerService;
import com.mastercard.billpay.consumer.service.EmailWhitelistService;
import com.mastercard.billpay.consumer.utils.PassCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerServiceImpl implements ConsumerService {

  @Autowired private ConsumerDao consumerDao;

  @Autowired private EmailWhitelistService emailWhitelistService;

  @Autowired private PassCodeGenerator consumerPasscodeGenerate;

  @Value("${app.security.passcodeLength}")
  private String passcodeLength;

  @Override
  public String signUpConsumer(ConsumerDto dto) throws ServiceException {
    log.info("registerConsumer");
    if (!(emailWhitelistService.isEmailWhitelisted(dto.getEmail()))) {
      throw new ServiceException("Email is not whitelisted");
    }
    String nonce = consumerPasscodeGenerate.createNonce();
    dto.setNonce(nonce);
    dto.setSaltedHash(consumerPasscodeGenerate.createSecurePassword("password1234", nonce));
    String consumerNonce = consumerDao.signUpConsumer(dto);
    return consumerNonce;
  }
}
