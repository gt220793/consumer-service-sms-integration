package com.mastercard.billpay.consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

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
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class TwilioSmsSenderTest {

  @Autowired private TwilioSmsSenderService twilioSmsSender;

  private TwilioSmsDto twilioSmsDto;

  @Autowired private Validator validator;

  @MockBean private TwilioSmsRepository twilioSmsRepository;

  @MockBean private TwilioSmsDao twilioSmsDao;

  @MockBean private OtpUtils otpUtils;

  @BeforeEach
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
    twilioSmsDto = TwilioSmsDto.builder().build();
    twilioSmsDto.setEmailId("unittesting@gmail.com");
    twilioSmsDto.setPhoneNumber("+918123914597");
    twilioSmsDto.setOtp("1234");
  }

  @Test
  public void testSendOtp_success() throws InvalidKeyException, NoSuchAlgorithmException {
    Mockito.when(twilioSmsDao.save(any(TwilioSmsDto.class))).thenReturn(twilioSmsDto);
    doNothing().when(otpUtils).sendOtp(new PhoneNumber(""), new PhoneNumber(""), "Otp Sent");
    String message = twilioSmsSender.sendOTP(twilioSmsDto);
    assertEquals("OTP sent successfully", message);
  }

  @Test
  public void testResendOtp_success() throws InvalidKeyException, NoSuchAlgorithmException {
    Mockito.when(twilioSmsDao.save(any(TwilioSmsDto.class))).thenReturn(twilioSmsDto);
    doNothing().when(otpUtils).sendOtp(new PhoneNumber(""), new PhoneNumber(""), "Otp Sent");
    Mockito.when(twilioSmsDao.getEmailCount(twilioSmsDto.getEmailId())).thenReturn(1);
    ResponseEntity<String> message = twilioSmsSender.resendOTP(twilioSmsDto);
    assertEquals("OTP resent successfully", message.getBody());
    // twilioSmsRepository.save(dto);
  }

  @Test
  public void testReSendOtp_failure() throws InvalidKeyException, NoSuchAlgorithmException {
    Mockito.when(twilioSmsDao.save(any(TwilioSmsDto.class))).thenReturn(twilioSmsDto);
    twilioSmsDto.setEmailId("");
    ResponseEntity<String> message = twilioSmsSender.resendOTP(twilioSmsDto);
    assertEquals("Invalid emailId", message.getBody());
  }

  @Test
  public void testVerifyOtp_success() throws InvalidKeyException, NoSuchAlgorithmException {
    Mockito.when(twilioSmsRepository.getSmsDetails(any(String.class)))
        .thenReturn(
            TwilioSms.builder()
                .expiryTs(new Timestamp(System.currentTimeMillis() + 5 * 60 * 1000))
                .otp(OtpUtils.otpToHashConverter("1234"))
                .build());
    ResponseEntity<String> verifyOtpResponse = twilioSmsSender.verifyOTP(twilioSmsDto);
    assertEquals("OTP verified successfully", verifyOtpResponse.getBody());
  }

  @Test
  public void testVerifyOtp_expired() throws InvalidKeyException, NoSuchAlgorithmException {
    Mockito.when(twilioSmsRepository.getSmsDetails(any(String.class)))
        .thenReturn(
            TwilioSms.builder()
                .expiryTs(new Timestamp(System.currentTimeMillis()))
                .otp("1234")
                .build());
    doReturn(
            TwilioSms.builder()
                .expiryTs(new Timestamp(System.currentTimeMillis()))
                .otp("1234")
                .build())
        .when(twilioSmsRepository)
        .getSmsDetails(any(String.class));
    ResponseEntity<String> verifyOtpResponse = twilioSmsSender.verifyOTP(twilioSmsDto);
    assertEquals("OTP is expired", verifyOtpResponse.getBody());
  }

  @Test
  public void testVerifyOtp_invalid() throws InvalidKeyException, NoSuchAlgorithmException {
    Mockito.when(twilioSmsRepository.getSmsDetails(any(String.class)))
        .thenReturn(
            TwilioSms.builder()
                .expiryTs(new Timestamp(System.currentTimeMillis() + 5 * 60 * 1000))
                .otp(OtpUtils.otpToHashConverter("1234"))
                .build());
    twilioSmsDto.setOtp("2345");
    ResponseEntity<String> verifyOtpResponse = twilioSmsSender.verifyOTP(twilioSmsDto);
    assertEquals("Invalid OTP", verifyOtpResponse.getBody());
  }
}
