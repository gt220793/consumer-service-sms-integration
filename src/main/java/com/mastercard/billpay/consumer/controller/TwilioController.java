package com.mastercard.billpay.consumer.controller;

import com.mastercard.billpay.consumer.dto.TwilioSmsDto;
import com.mastercard.billpay.consumer.service.TwilioSmsSenderService;
import com.mastercard.billpay.consumer.utils.BillPayApiResponse;
import com.mastercard.billpay.consumer.utils.BillPayUtils;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/sms")
public class TwilioController {

  private final TwilioSmsSenderService twilioSmsSender;

  @Autowired
  private TwilioController(TwilioSmsSenderService twilioSmsSender) {
    this.twilioSmsSender = twilioSmsSender;
  }

  @PostMapping("/generation")
  public String sendSms(@Valid @RequestBody TwilioSmsDto twilioSmsDto)
      throws InvalidKeyException, NoSuchAlgorithmException {
    return twilioSmsSender.sendOTP(twilioSmsDto);
  }

  @PostMapping("/verification")
  public ResponseEntity<BillPayApiResponse<TwilioSmsDto>> verifySms(
      @Valid @RequestBody TwilioSmsDto verifyOtpDto) {
    try {
      ResponseEntity<String> smsSrvcResponse = twilioSmsSender.verifyOTP(verifyOtpDto);
      if (smsSrvcResponse.getStatusCodeValue() == 200) {
        return ResponseEntity.ok(
            BillPayUtils.buildResponse(
                Collections.emptyList(),
                smsSrvcResponse.getBody().toString(),
                smsSrvcResponse.getStatusCodeValue()));
      } else {
        return ResponseEntity.badRequest()
            .body(
                BillPayUtils.buildResponse(
                    Collections.emptyList(),
                    smsSrvcResponse.getBody().toString(),
                    smsSrvcResponse.getStatusCodeValue()));
      }
    } catch (Exception e) {
      log.error("VerifyOTP::verifyOtp() failed ");
      return ResponseEntity.ok(
          BillPayUtils.buildResponse(Collections.<TwilioSmsDto>emptyList(), e.getMessage(), 1234));
    }
  }

  @PostMapping("/resend")
  public ResponseEntity<BillPayApiResponse<TwilioSmsDto>> resendSms(
      @Valid @RequestBody TwilioSmsDto resendOtpDto) {
    try {
      ResponseEntity<String> smsSrvcResponse = twilioSmsSender.resendOTP(resendOtpDto);
      if (smsSrvcResponse.getStatusCodeValue() == 200) {
        return ResponseEntity.ok(
            BillPayUtils.buildResponse(
                Collections.emptyList(),
                smsSrvcResponse.getBody().toString(),
                smsSrvcResponse.getStatusCodeValue()));
      } else {
        return ResponseEntity.ok(
            BillPayUtils.buildResponse(
                Collections.emptyList(),
                smsSrvcResponse.getBody().toString(),
                smsSrvcResponse.getStatusCodeValue()));
      }
    } catch (Exception e) {
      log.error("ResendOTP::resendOtp() failed ");
      return ResponseEntity.ok(
          BillPayUtils.buildResponse(Collections.<TwilioSmsDto>emptyList(), e.getMessage(), 1234));
    }
  }
}
