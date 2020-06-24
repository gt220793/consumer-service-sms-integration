package com.mastercard.billpay.consumer.utils;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class OtpUtils {

  public void sendOtp(PhoneNumber to, PhoneNumber from, String message) {
    MessageCreator creator = Message.creator(to, from, message);
    creator.create();
  }

  public static String otpToHashConverter(String otp)
      throws InvalidKeyException, NoSuchAlgorithmException {
    String secret = "secret";
    Mac sha256HMAC = Mac.getInstance("HmacSHA256");
    SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
    sha256HMAC.init(secretKey);
    return Base64.encodeBase64String(sha256HMAC.doFinal(otp.getBytes()));
  }
}
