package com.mastercard.billpay.consumer.db.entity;

import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Consumer Profile entity represents a table in the database schema
 *
 * @author Rahul Jadhao
 * @since 1.0
 */
@Entity
@Table(name = "consumer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Consumer extends BaseEntity {

  /*@Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")*/
  @Column(name = "consumerRefNumber", length = 36)
  private String consumerRefNumber;

  @Column(name = "firstName", columnDefinition = "text")
  private String firstName;

  @Column(name = "lastName", columnDefinition = "text")
  private String lastName;

  @Column(name = "email", columnDefinition = "text")
  private String email;

  @Column(name = "consumerId", length = 50)
  private String consumerId;

  @Column(name = "mobileNumber", length = 10)
  private String mobileNumber;

  @Column(name = "saltedHash", columnDefinition = "text")
  private String saltedHash;

  @Column(name = "nonce")
  private String nonce;

  @PrePersist
  public void populateId() {
    setId(UUID.randomUUID().toString());
  }
}
