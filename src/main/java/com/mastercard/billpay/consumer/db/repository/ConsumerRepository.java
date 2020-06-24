package com.mastercard.billpay.consumer.db.repository;

import com.mastercard.billpay.consumer.db.entity.Consumer;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * JPA repository to interact with the database entity
 *
 * @author Rahul Jadhao
 * @since 1.0
 */
@Transactional
@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, String> {

  @Query(value = "select * from consumer ent where ent.email = :emailAddress", nativeQuery = true)
  Optional<Consumer> getConsumerDetailsByEmailAddress(@Param("emailAddress") String emailAddress);
}
