package com.mastercard.billpay.consumer.db.dao;

import com.mastercard.billpay.consumer.db.entity.TwilioSms;
import com.mastercard.billpay.consumer.dto.TwilioSmsDto;
import com.mastercard.billpay.consumer.exception.DaoException;
import java.util.List;

/**
 * Dao layer for managing TwilioSms entity. The entity and database exceptions need to be handled
 * here. The Dao layer can only exchange DTO or Custom Dao exceptions with the outer packages
 *
 * @author Gourav Tripathi
 * @since 1.0
 */
public interface TwilioSmsDao extends BaseDao<TwilioSmsDto, TwilioSms> {

  /**
   * save list of twilio sms in the system
   *
   * @param twilioSmsToSave to be looked up
   */
  public List<TwilioSmsDto> saveAll(List<TwilioSmsDto> twilioSmsToSave);

  public TwilioSmsDto save(TwilioSmsDto twilioSmsToSave);

  /**
   * get the newly generated otp.
   *
   * @throws DaoException in case of error comes
   */
  TwilioSms getSmsDetails(String emailId);

  /**
   * get the count of records for given emailId.
   *
   * @throws DaoException in case of error comes
   */
  Integer getEmailCount(String emailId);

  /**
   * fetch all twilioSmsListed from database
   *
   * @return {@link List < TwilioSmsDto >} list of dto emailWhitelist object
   */
  public List<TwilioSmsDto> getAllTwilioSmsDetails();

  @Override
  default void transformDtoToEntity(final TwilioSmsDto dto, TwilioSms entity) {
    entity.setPhoneNumber(dto.getPhoneNumber());
    entity.setEmailId(dto.getEmailId());
    entity.setCreatedTs(dto.getCreatedTimeStamp());
    entity.setOtp(dto.getOtp());
    entity.setExpiryTs(dto.getExpiryTime());
  }

  @Override
  default void transformEntityToDto(final TwilioSms entity, TwilioSmsDto dto) {
    dto.setPhoneNumber(entity.getPhoneNumber());
    dto.setEmailId(entity.getEmailId());
    dto.setOtp(entity.getOtp());
  }
}
