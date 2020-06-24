package com.mastercard.billpay.consumer.utils;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillPayApiResponse<T> {

  private int messageCode;
  private String message;
  private List<T> data;
}
