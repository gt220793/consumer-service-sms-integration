package com.mastercard.billpay.consumer.service.impl;

import com.mastercard.billpay.consumer.db.entity.EmailWhitelist;
import com.mastercard.billpay.consumer.db.repository.EmailWhitelistRepository;
import com.mastercard.billpay.consumer.exception.ServiceException;
import com.mastercard.billpay.consumer.service.EmailWhitelistService;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Implementation of actions supported for Email Whitelisting as per business logic
 *
 * @author Gourav Tripathi
 * @since 1.0
 */
@Service
// @Slf4j
public class EmailWhitelistServiceImpl implements EmailWhitelistService {

  @Autowired private EmailWhitelistRepository emailWhitelistRepository;

  private static final String EMAILREGEX =
      "^[a-zA-Z0-9_+&*-]+(?:\\."
          + "[a-zA-Z0-9_+&*-]+)*@"
          + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
          + "A-Z]{2,7}$";

  @Value("${app.featureToggles.emailWhitelist}")
  private String emailWhitelistFlag;

  /**
   * verify mailId falls under whiitelistedEmails list from the system
   *
   * @return {Boolean} result for whitelistedEmails
   */
  @Override
  public Boolean isEmailWhitelisted(String emailId) {
    if (isValid(emailId)) {
      boolean flag = Boolean.parseBoolean(emailWhitelistFlag);
      if (flag) {
        EmailWhitelist emailWhitelist = emailWhitelistRepository.findByEmailId(emailId);
        boolean result = false;
        if (emailWhitelist != null && emailWhitelist.getEmailId().equals(emailId)) {
          result = true;
          return result;
        } else return result;
      }
      return true;
    } else throw new ServiceException("Email Id is invalid");
  }

  private boolean isValid(String email) {

    Pattern pat = Pattern.compile(EMAILREGEX);
    return pat.matcher(email).matches();
  }
}
