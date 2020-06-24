package com.mastercard.billpay.consumer.db.repository;

import com.mastercard.billpay.consumer.db.entity.EmailWhitelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository to interact with the database entity
 *
 * @author Gourav Tripathi
 * @since 1.0
 */
@Repository
public interface EmailWhitelistRepository extends JpaRepository<EmailWhitelist, String> {

  EmailWhitelist findByEmailId(String emailId);
}
