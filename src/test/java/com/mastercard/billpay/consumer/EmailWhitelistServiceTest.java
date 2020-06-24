/*
package com.mastercard.billpay.consumer.consumer;

import com.mastercard.billpay.consumer.db.entity.EmailWhitelist;
import com.mastercard.billpay.consumer.db.repository.EmailWhitelistRepository;
import com.mastercard.billpay.consumer.dto.EmailWhitelistDto;
import com.mastercard.billpay.consumer.exception.ServiceException;
import com.mastercard.billpay.consumer.service.EmailWhitelistService;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailWhitelistServiceTest {

  @Autowired private EmailWhitelistService emailWhitelistService;

  @Autowired private Validator validator;

  @MockBean private EmailWhitelistRepository emailWhitelistRepository;

  @Before
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void testIsEmailWhitelisted_true() {
    String emailId = "abc@gmail.com";
    Mockito.when(emailWhitelistRepository.findByEmailId(emailId))
        .thenReturn(getTestIsEmailWhitelistTrue());
    Boolean isEmailWhitelited = emailWhitelistService.isEmailWhitelisted(emailId);
    Assertions.assertTrue(isEmailWhitelited);
  }

  private EmailWhitelist getTestIsEmailWhitelistTrue() {
    EmailWhitelist emailWhitelist = new EmailWhitelist();
    emailWhitelist.setConsumerId("CUST_IICA_RPPS_TEST6Y3V6");
    emailWhitelist.setEmailId("abc@gmail.com");
    return emailWhitelist;
  }

  @Test
  public void testIsEmailNotWhitelisted_false() {
    String emailId = "abc11@gmail.com";
    Mockito.when(emailWhitelistRepository.findByEmailId(emailId))
        .thenReturn(getTestIsEmailWhitelistFalse());
    Boolean isEmailWhitelisted = emailWhitelistService.isEmailWhitelisted(emailId);
    Assertions.assertFalse(isEmailWhitelisted);
  }

  private EmailWhitelist getTestIsEmailWhitelistFalse() {
    EmailWhitelist emailWhitelist = new EmailWhitelist();
    emailWhitelist.setConsumerId("CUST_IICA_RPPS_TEST6Y3V6");
    emailWhitelist.setEmailId("abc@gmail.com");
    return emailWhitelist;
  }

  @Test()
  public void testIsEmailInvalid() {
    String emailId = "abc1";
    Assertions.assertThrows(
        ServiceException.class, () -> emailWhitelistService.isEmailWhitelisted(emailId));
  }

  @Test
  public void testSaveEmailWhitelisted() {
    EmailWhitelistDto dto = EmailWhitelistDto.builder().build();
    dto.setEmailId("testing@test.com");
    dto.setConsumerId("CUST_IICA_RPPS_TEST6Y3V6");
    Set<ConstraintViolation<EmailWhitelistDto>> violations = validator.validate(dto);
    Assertions.assertTrue(violations.isEmpty());
  }
}
*/
