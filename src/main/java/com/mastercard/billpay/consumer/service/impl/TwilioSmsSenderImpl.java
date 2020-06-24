package com.mastercard.billpay.consumer.service.impl;

import com.mastercard.billpay.consumer.config.twilio.TwilioConfiguration;
import com.mastercard.billpay.consumer.db.dao.TwilioSmsDao;
import com.mastercard.billpay.consumer.db.entity.TwilioSms;
import com.mastercard.billpay.consumer.db.repository.TwilioSmsRepository;
import com.mastercard.billpay.consumer.dto.TwilioSmsDto;
import com.mastercard.billpay.consumer.service.TwilioSmsSenderService;
import com.mastercard.billpay.consumer.utils.OtpUtils;
import com.twilio.type.PhoneNumber;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsSenderImpl implements TwilioSmsSenderService {

  private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSenderImpl.class);

  private final TwilioConfiguration twilioConfiguration;

  private final TwilioSmsDao twilioSmsDao;

  private final TwilioSmsRepository twilioSmsRepository;

  private final OtpUtils otpUtils;

  private Map<String, TwilioSmsDto> otpData = new HashMap<>();

  private final Random random = new Random();

  @Value("${app.featureToggles.otpGeneration}")
  private String otpGenerationFlag;

  @Value("${twilio.smsExpLmt}")
  private Long smsExpLmt;

  @Value("${twilio.otpLengthUpperLmt}")
  private String otpLengthUpperLmt;

  @Value("${twilio.otpLengthLowerLmt}")
  private String otpLengthLowerLmt;

  @Autowired
  public TwilioSmsSenderImpl(
      TwilioConfiguration twilioConfiguration,
      TwilioSmsDao twilioSmsDao,
      TwilioSmsRepository twilioSmsRepository,
      OtpUtils otpUtils) {
    this.twilioConfiguration = twilioConfiguration;
    this.twilioSmsDao = twilioSmsDao;
    this.twilioSmsRepository = twilioSmsRepository;
    this.otpUtils = otpUtils;
  }

  @Override
  public String sendOTP(TwilioSmsDto twilioSmsDto)
      throws NoSuchAlgorithmException, InvalidKeyException {
    Boolean appFlag = Boolean.parseBoolean(otpGenerationFlag);
    if (Boolean.TRUE.equals(appFlag)) {
      PhoneNumber to = new PhoneNumber(twilioSmsDto.getPhoneNumber());
      PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
      Long expiryTime = System.currentTimeMillis() + smsExpLmt;
      twilioSmsDto.setExpiryTime(new Timestamp(expiryTime));
      otpData.put(twilioSmsDto.getEmailId(), twilioSmsDto);
      twilioSmsDto.setOtp(
          String.valueOf(
              ((random.nextInt(
                      Integer.parseInt(otpLengthUpperLmt) - Integer.parseInt(otpLengthLowerLmt)))
                  + Integer.parseInt(otpLengthLowerLmt))));
      String message = "Your OTP is : " + twilioSmsDto.getOtp();
      twilioSmsDto.setOtp(OtpUtils.otpToHashConverter(twilioSmsDto.getOtp()));
      twilioSmsDto.setCreatedTimeStamp(new Timestamp(System.currentTimeMillis()));
      twilioSmsDao.save(twilioSmsDto);
      otpUtils.sendOtp(to, from, message);
      LOGGER.info("Sending sms {}......", 1);
      return "OTP sent successfully";
    } else return "Application flag is disabled for this phone number";
  }

  @Override
  public ResponseEntity<String> resendOTP(TwilioSmsDto resendSmsDto)
      throws NoSuchAlgorithmException, InvalidKeyException {
    if (twilioSmsDao.getEmailCount(resendSmsDto.getEmailId()) <= 0
        || resendSmsDto.getEmailId() == null
        || resendSmsDto.getEmailId().trim().length() <= 0) {
      return new ResponseEntity<>("Invalid emailId", HttpStatus.BAD_REQUEST);
    } else {
      PhoneNumber to = new PhoneNumber(resendSmsDto.getPhoneNumber());
      PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
      Long expiryTime = System.currentTimeMillis() + smsExpLmt;
      resendSmsDto.setExpiryTime(new Timestamp(expiryTime));
      resendSmsDto.setOtp(
          String.valueOf(
              ((random.nextInt(
                      Integer.parseInt(otpLengthUpperLmt) - Integer.parseInt(otpLengthLowerLmt)))
                  + Integer.parseInt(otpLengthLowerLmt))));
      String message = "Your OTP is : " + resendSmsDto.getOtp();
      resendSmsDto.setOtp(OtpUtils.otpToHashConverter(resendSmsDto.getOtp()));
      resendSmsDto.setCreatedTimeStamp(new Timestamp(System.currentTimeMillis()));
      twilioSmsDao.save(resendSmsDto);
      otpUtils.sendOtp(to, from, message);
      LOGGER.info("Resending sms {}......", 1);
      return new ResponseEntity<>("OTP resent successfully", HttpStatus.OK);
    }
  }

  @Override
  public ResponseEntity<String> verifyOTP(TwilioSmsDto verifyOtpDto)
      throws NoSuchAlgorithmException, InvalidKeyException {
    if (verifyOtpDto.getOtp() == null || verifyOtpDto.getOtp().trim().length() <= 0) {
      return new ResponseEntity<>("Please provide OTP", HttpStatus.BAD_REQUEST);
    } else {
      TwilioSms twilioSms = twilioSmsRepository.getSmsDetails(verifyOtpDto.getEmailId());
      Long expiryTimeDb = twilioSms.getExpiryTs().getTime();
      if (expiryTimeDb >= System.currentTimeMillis()) {
        if (twilioSms.getOtp().equals(OtpUtils.otpToHashConverter(verifyOtpDto.getOtp()))) {
          return new ResponseEntity<>("OTP verified successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
      }
      return new ResponseEntity<>("OTP is expired", HttpStatus.BAD_REQUEST);
    }
  }
}
