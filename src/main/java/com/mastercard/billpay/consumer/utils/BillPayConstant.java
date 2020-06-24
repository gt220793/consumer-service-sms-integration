package com.mastercard.billpay.consumer.utils;

public class BillPayConstant {

  /** URL related details */
  public static final String BASIC_URL = "http://localhost:";

  public static final String SIGNUP_URI = "/v1/consumer/signUp";

  /** JSON file related details */
  public static final String SIGNUP_VALID_JSON = "/mock/singUp/signUpValid";

  public static final String SIGNUP_INVALID_EMAIL_JSON = "/mock/singUp/signUpInvalidEmail";

  public static final String SIGNUP_MISSING_EMAIL_JSON = "/mock/singUp/signUpMissingEmail";

  public static final String SIGNUP_MISSING_FIRSTNAME_JSON = "/mock/singUp/signUpMissingFirstName";

  public static final String SIGNUP_MISSING_LASTNAME_JSON = "/mock/singUp/signUpMissingLastName";

  public static final String SIGNUP_MISSING_MOBILE_NUMBER_JSON =
      "/mock/singUp/signUpMissingMobileNumber";
}
