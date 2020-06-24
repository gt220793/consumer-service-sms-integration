package com.mastercard.billpay.consumer.db.entity;

import java.util.UUID;
import javax.persistence.*;
import lombok.Data;

/**
 * EmailWhitelist entity represents a table in the database schema
 *
 * @author Gourav Tripathi
 * @since 1.0
 */
@Entity
@Table(name = "email_whitelisted")
@Data
public class EmailWhitelist extends BaseEntity {

  public static final String COLUMN_CONSUMER_ID = "consumer_id";
  public static final String COLUMN_EMAIL_ID = "email_id";

  @Id private String id;

  @Column(name = COLUMN_CONSUMER_ID)
  private String consumerId;

  @Column(name = COLUMN_EMAIL_ID, nullable = false)
  private String emailId;

  @PrePersist
  public void populateId() {
    setId(UUID.randomUUID().toString());
  }
}
