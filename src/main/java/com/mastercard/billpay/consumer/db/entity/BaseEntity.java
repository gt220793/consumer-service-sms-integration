package com.mastercard.billpay.consumer.db.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

/**
 * Base entity for all domain objects in the db schema
 *
 * @author Mukhtar Sayyed
 * @since 1.0
 */
@MappedSuperclass
@Data
public class BaseEntity implements Serializable {

  @Id private String id;

  @Column(name = "created_ts")
  private Timestamp createdTs;

  @Column(name = "updated_ts")
  private Timestamp updatedTs;

  @Column(name = "created_by", columnDefinition = "text")
  private String createdBy;

  @Column(name = "updated_by", columnDefinition = "text")
  private String updatedBy;

  @Column(name = "version")
  private Integer version;
}
