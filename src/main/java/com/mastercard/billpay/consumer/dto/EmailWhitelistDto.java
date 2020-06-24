package com.mastercard.billpay.consumer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailWhitelistDto extends BaseDto {
  private String consumerId;
  private String emailId;
}
