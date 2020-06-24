package com.mastercard.billpay.consumer.exception;

/**
 * custom exception for data access related errors
 *
 * @author Mukhtar Sayyed
 * @since 1.0
 */
public class DaoException extends BaseException {
  public DaoException(String message) {
    super(message);
  }
}
