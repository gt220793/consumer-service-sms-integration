package com.mastercard.billpay.consumer.exception;

/**
 * Base exception is the super class of all custom exceptions
 *
 * @author Mukhtar Sayyed
 * @since 1.0
 */
abstract class BaseException extends RuntimeException {

  public BaseException(String message) {
    super(message);
  }
}
