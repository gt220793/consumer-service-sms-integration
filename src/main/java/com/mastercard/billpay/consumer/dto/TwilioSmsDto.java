package com.mastercard.billpay.consumer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO defination for verify otp
 *
 * @author Gourav tripathi
 * @since 1.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TwilioSmsDto extends BaseDto {
  private String phoneNumber;
  private String emailId;
  private String otp;
  private String message;
  private Timestamp expiryTime;
  private Timestamp createdTimeStamp;
}
