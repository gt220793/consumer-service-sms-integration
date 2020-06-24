package com.mastercard.billpay.consumer.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TwilioSmsRequest {

  @NotBlank private final String phoneNumber; // destination
  private String message;
  private String otp;
  private Long expiryTime;

  public TwilioSmsRequest(@JsonProperty("phoneNumber") String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Override
  public String toString() {
    return "SmsRequest{"
        + "phoneNumber='"
        + phoneNumber
        + '\''
        + ", message='"
        + message
        + '\''
        + '}';
  }
}
