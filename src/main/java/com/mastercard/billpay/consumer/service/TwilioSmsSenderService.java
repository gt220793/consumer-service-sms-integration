package com.mastercard.billpay.consumer.service;

import com.mastercard.billpay.consumer.dto.TwilioSmsDto;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.springframework.http.ResponseEntity;

public interface TwilioSmsSenderService {

  String sendOTP(TwilioSmsDto twilioSmsDto) throws NoSuchAlgorithmException, InvalidKeyException;

  ResponseEntity<String> verifyOTP(TwilioSmsDto verifyOtpDto)
      throws NoSuchAlgorithmException, InvalidKeyException;

  ResponseEntity<String> resendOTP(TwilioSmsDto resendOtpDto)
      throws NoSuchAlgorithmException, InvalidKeyException;
}
