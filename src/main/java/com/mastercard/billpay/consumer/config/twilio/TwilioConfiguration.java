package com.mastercard.billpay.consumer.config.twilio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Configuration
@ConfigurationProperties("twilio")
public class TwilioConfiguration {

  private String accountSid;
  private String authToken;
  private String trialNumber;
}
