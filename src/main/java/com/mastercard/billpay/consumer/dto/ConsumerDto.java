package com.mastercard.billpay.consumer.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumerDto extends BaseDto {

  private String consumerRefNumber;

  @Size(min = 3, max = 50, message = "firstName must be between 3 and 50 characters")
  @NotNull(message = "firstName cannot be null")
  private String firstName;

  @Size(min = 3, max = 50, message = "lastName must be between 3 and 50 characters")
  @NotNull(message = "lastName cannot be null")
  private String lastName;

  @Email(message = "email should be valid")
  @NotNull(message = "email cannot be null")
  private String email;

  @NotNull(message = "mobileNumber cannot be null")
  private String mobileNumber;

  private String consumerId;

  private String saltedHash;

  private String nonce;
}
