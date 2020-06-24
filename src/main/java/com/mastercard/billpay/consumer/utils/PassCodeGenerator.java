package com.mastercard.billpay.consumer.utils;

import com.mastercard.billpay.consumer.exception.ServiceException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PassCodeGenerator implements Serializable {

  private static final long serialVersionUID = -2550185165626007488L;

  @Value("${app.security.hashingAlgorithm}")
  private String hashingAlgorithm;

  @Value("${app.security.nonceLength}")
  private String nonceLength;

  public String createSecurePassword(String password, String nonce) throws ServiceException {
    try {
      byte[] decodeByte = Base64.getDecoder().decode(nonce);
      List<Byte> alternateNonceChar =
          IntStream.range(0, decodeByte.length)
              .filter(n -> n % 2 == 0)
              .mapToObj(n -> decodeByte[n])
              .collect(Collectors.toList());

      String alternateNonceString =
          alternateNonceChar.stream().map(String::valueOf).collect(Collectors.joining());
      MessageDigest md = null;

      md = MessageDigest.getInstance(hashingAlgorithm);
      md.update(alternateNonceString.getBytes());
      byte[] bytes = md.digest(password.getBytes());
      // This bytes[] has bytes in decimal format;
      // Convert it to hexadecimal format
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < bytes.length; i++) {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }
      // Get complete hashed password in hex format
      String generatedPassword = sb.toString();
      return generatedPassword;
    } catch (NoSuchAlgorithmException e) {
      throw new ServiceException("Passcode generation failed");
    }
  }

  public String createNonce() {
    int nonceLengthInInt = Integer.parseInt(nonceLength);
    SecureRandom random = new SecureRandom();
    byte bytes[] = new byte[nonceLengthInInt];
    random.nextBytes(bytes);
    return (Base64.getEncoder().encodeToString(bytes));
  }
}
