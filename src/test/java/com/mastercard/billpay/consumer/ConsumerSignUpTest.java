/*
package com.mastercard.billpay.consumer.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercard.billpay.consumer.db.dao.ConsumerDao;
import com.mastercard.billpay.consumer.dto.ConsumerDto;
import com.mastercard.billpay.consumer.exception.ServiceException;
import com.mastercard.billpay.consumer.service.ConsumerService;
import com.mastercard.billpay.consumer.service.EmailWhitelistService;
import com.mastercard.billpay.consumer.utils.BillPayConstant;
import com.mastercard.billpay.consumer.utils.PassCodeGenerator;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.json.simple.parser.ParseException;
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
public class ConsumerSignUpTest {

  @Autowired private ConsumerService consumerService;

  @MockBean private ConsumerDao dao;

  @MockBean EmailWhitelistService emailWhitelistService;

  @Autowired private Validator validator;

  @MockBean private PassCodeGenerator consumerPasscodeGenerate;

  @Before
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void testSignUpWithValidInput() throws Exception {
    // Get the data from json file
    ConsumerDto dto = readDataFromJsonFile(BillPayConstant.SIGNUP_VALID_JSON);

    String passcode = "password1234";

    Set<ConstraintViolation<ConsumerDto>> violations = validator.validate(dto);
    Assertions.assertTrue(violations.isEmpty());

    Mockito.when(emailWhitelistService.isEmailWhitelisted(dto.getEmail())).thenReturn(true);

    Mockito.when(consumerPasscodeGenerate.createNonce()).thenReturn(giveNonce());

    Mockito.when(consumerPasscodeGenerate.createSecurePassword(passcode, giveNonce()))
        .thenReturn(giveSaltedHash());

    Mockito.when(dao.signUpConsumer(dto)).thenReturn(giveNonce());

    String consumerNonce = consumerService.signUpConsumer(dto);

    Assertions.assertNotNull(consumerNonce);
  }

  @Test
  public void testSignWithWhitelistEmail() throws Exception {
    // Get the data from json file
    ConsumerDto dto = readDataFromJsonFile(BillPayConstant.SIGNUP_VALID_JSON);

    String passcode = "password1234";

    Set<ConstraintViolation<ConsumerDto>> violations = validator.validate(dto);
    Assertions.assertTrue(violations.isEmpty());

    Mockito.when(emailWhitelistService.isEmailWhitelisted(dto.getEmail())).thenReturn(false);

    Mockito.when(consumerPasscodeGenerate.createNonce()).thenReturn(giveNonce());

    Mockito.when(consumerPasscodeGenerate.createSecurePassword(passcode, giveNonce()))
        .thenReturn(giveSaltedHash());
    Mockito.when(dao.signUpConsumer(dto)).thenReturn(giveNonce());
    Exception exception =
        Assertions.assertThrows(
            ServiceException.class,
            () -> {
              consumerService.signUpConsumer(dto);
            });
    Assertions.assertTrue(exception.getMessage().contains("Email is not whitelisted"));
  }

  @Test
  public void testSignUpWithInvalidEmail() throws Exception {
    // Get the data from json file
    ConsumerDto dto = readDataFromJsonFile(BillPayConstant.SIGNUP_INVALID_EMAIL_JSON);
    Set<ConstraintViolation<ConsumerDto>> violations = validator.validate(dto);
    for (ConstraintViolation<ConsumerDto> cpValidation : violations) {
      Assertions.assertTrue(cpValidation.getMessage().equals("email should be valid"));
    }
  }

  @Test
  public void testSignUpMissingEmail() throws Exception {
    // Get the data from json file
    ConsumerDto dto = readDataFromJsonFile(BillPayConstant.SIGNUP_MISSING_EMAIL_JSON);
    Set<ConstraintViolation<ConsumerDto>> violations = validator.validate(dto);
    for (ConstraintViolation<ConsumerDto> cpValidation : violations) {
      Assertions.assertTrue(cpValidation.getMessage().equals("email cannot be null"));
    }
  }

  @Test
  public void testSignUpMissingFirstName() throws Exception {
    // Get the data from json file
    ConsumerDto dto = readDataFromJsonFile(BillPayConstant.SIGNUP_MISSING_FIRSTNAME_JSON);
    Set<ConstraintViolation<ConsumerDto>> violations = validator.validate(dto);
    for (ConstraintViolation<ConsumerDto> cpValidation : violations) {
      Assertions.assertTrue(cpValidation.getMessage().equals("firstName cannot be null"));
    }
  }

  @Test
  public void testSignUpMissingLastName() throws Exception {
    // Get the data from json file
    ConsumerDto dto = readDataFromJsonFile(BillPayConstant.SIGNUP_MISSING_LASTNAME_JSON);
    Set<ConstraintViolation<ConsumerDto>> violations = validator.validate(dto);
    for (ConstraintViolation<ConsumerDto> cpValidation : violations) {
      Assertions.assertTrue(cpValidation.getMessage().equals("lastName cannot be null"));
    }
  }

  @Test
  public void testSignUpMissingMobileNumber() throws Exception {
    // Get the data from json file
    ConsumerDto dto = readDataFromJsonFile(BillPayConstant.SIGNUP_MISSING_MOBILE_NUMBER_JSON);
    Set<ConstraintViolation<ConsumerDto>> violations = validator.validate(dto);
    for (ConstraintViolation<ConsumerDto> cpValidation : violations) {
      Assertions.assertTrue(cpValidation.getMessage().equals("mobileNumber cannot be null"));
    }
  }

  private String giveNonce() {
    return "WKaHZbwd5HYFzRPYwT31GCYYNQJJcLrGVeUcdzoRW7bG7n6xuI5CHg7oJ3glcg1LMzu6lI5G3M19rZIjv/5ziA==";
  }

  private String giveSaltedHash() {
    return "5eec540136cc8b8b1d7b852d1deb59045c6f3d1a6422f6dbc2e98e60f1ebb1e0bb2da9cae670f13379211509ac6c50ebceca9d9694d20790d7b0facc3dce261d";
  }

  private ConsumerDto readDataFromJsonFile(String fileName) throws IOException, ParseException {
    InputStream inputStream = getClass().getResourceAsStream(fileName);
    String content = new String(inputStream.readAllBytes());
    ObjectMapper mapper = new ObjectMapper();
    ConsumerDto dto = mapper.readValue(content, ConsumerDto.class);
    return dto;
  }
}
*/
