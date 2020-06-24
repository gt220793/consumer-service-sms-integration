package com.mastercard.billpay.consumer.db.dao.impl;

import com.mastercard.billpay.consumer.db.dao.TwilioSmsDao;
import com.mastercard.billpay.consumer.db.entity.TwilioSms;
import com.mastercard.billpay.consumer.db.repository.TwilioSmsRepository;
import com.mastercard.billpay.consumer.dto.TwilioSmsDto;
import com.mastercard.billpay.consumer.exception.DaoException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementation of Dao Impl. This implementation interacts with the Repository and handle database
 * errors
 *
 * @author Gourav Tripathi
 * @since 1.0
 */
@Component
@Slf4j
public class TwilioSmsDaoImpl implements TwilioSmsDao {

  @Autowired private TwilioSmsRepository twilioSmsRepository;

  @Override
  public List<TwilioSmsDto> saveAll(List<TwilioSmsDto> sms) {
    List<TwilioSms> smsDetailList = new ArrayList<>();
    for (TwilioSmsDto smsDetailsDto : sms) {
      final TwilioSms twilioSms = TwilioSms.builder().build();
      transformDtoToEntity(smsDetailsDto, twilioSms);
      smsDetailList.add(twilioSms);
    }
    twilioSmsRepository.saveAll(smsDetailList);
    return sms;
  }

  @Override
  public TwilioSmsDto save(TwilioSmsDto sms) {
    final TwilioSms twilioSms = TwilioSms.builder().build();
    transformDtoToEntity(sms, twilioSms);
    twilioSmsRepository.save(twilioSms);
    return sms;
  }

  /**
   * get the newly generated otp.
   *
   * @param emailId
   * @throws DaoException in case of error comes
   */
  @Override
  public TwilioSms getSmsDetails(String emailId) {
    return twilioSmsRepository.getSmsDetails(emailId);
  }

  /**
   * get the count of records for given emailId.
   *
   * @throws DaoException in case of error comes
   */
  @Override
  public Integer getEmailCount(String emailId) {
    return twilioSmsRepository.getEmailCount(emailId);
  }

  @Override
  public List<TwilioSmsDto> getAllTwilioSmsDetails() {
    List<TwilioSmsDto> twilioSmsDtos = new ArrayList<>();
    List<TwilioSms> masterSmsDetails = twilioSmsRepository.findAll();
    for (TwilioSms sms : masterSmsDetails) {
      final TwilioSmsDto dto = TwilioSmsDto.builder().build();
      transformEntityToDto(sms, dto);
      twilioSmsDtos.add(dto);
    }
    return twilioSmsDtos;
  }
}
