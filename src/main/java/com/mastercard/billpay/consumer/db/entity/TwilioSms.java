package com.mastercard.billpay.consumer.db.entity;

import java.sql.Timestamp;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;

/**
 * EmailWhitelist entity represents a table in the database schema
 *
 * @author Gourav Tripathi
 * @since 1.0
 */
@Builder
@Entity
@Table(name = "twilio_sms")
@Data
public class TwilioSms extends BaseEntity {

  public static final String COLUMN_PHONE_NUMBER = "phone_number";
  public static final String COLUMN_EMAIL_ID = "email_id";
  public static final String COLUMN_OTP = "otp";
  public static final String COLUMN_EXPIRY_TS = "expiry_ts";

  @Column(name = COLUMN_PHONE_NUMBER)
  private String phoneNumber;

  @Column(name = COLUMN_EMAIL_ID, nullable = false)
  private String emailId;

  @Column(name = COLUMN_OTP)
  private String otp;

  @Column(name = COLUMN_EXPIRY_TS)
  private Timestamp expiryTs;

  @PrePersist
  public void populateId() {
    setId(UUID.randomUUID().toString());
  }
}
