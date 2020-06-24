package com.mastercard.billpay.consumer.service;

import com.mastercard.billpay.consumer.exception.ServiceException;

/**
 * Interface defining actions supported for WhitelistedEmails as per business logic
 *
 * @author Gourav Tripathi
 * @since 1.0
 */
public interface EmailWhitelistService {
  /**
   * verify mailId falls under whiitelistedEmails list from the system
   *
   * @return {Boolean} result for whitelistedEmails
   */
  Boolean isEmailWhitelisted(String emailId) throws ServiceException;
}
