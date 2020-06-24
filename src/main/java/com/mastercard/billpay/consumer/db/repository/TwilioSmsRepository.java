package com.mastercard.billpay.consumer.db.repository;

import com.mastercard.billpay.consumer.db.entity.TwilioSms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * JPA repository to interact with the database entity
 *
 * @author Gourav Tripathi
 * @since 1.0
 */
@Repository
public interface TwilioSmsRepository extends JpaRepository<TwilioSms, String> {

  @Query(
      value =
          "SELECT * FROM twilio_sms where email_id = :emailId order by created_ts Desc limit 1;",
      nativeQuery = true)
  // String getOtp(@Param("emailId") String emailId);
  TwilioSms getSmsDetails(@Param("emailId") String emailId);

  @Query(
      value =
          "SELECT count(*) FROM twilio_sms where email_id = :emailId order by created_ts Desc limit 1;",
      nativeQuery = true)
  Integer getEmailCount(@Param("emailId") String emailId);
}
