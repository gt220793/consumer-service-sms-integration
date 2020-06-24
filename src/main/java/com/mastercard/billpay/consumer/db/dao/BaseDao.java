package com.mastercard.billpay.consumer.db.dao;

import com.mastercard.billpay.consumer.db.entity.BaseEntity;
import com.mastercard.billpay.consumer.dto.BaseDto;

/**
 * This is the DAO(Data Access Object) layer interface for the conversion of DTO objects to their
 * respective entity object.
 *
 * @param <T extends BaseDto> dtoObject to be convert into entity object.
 * @param <E extends BaseEntity> entity object.
 * @author Mukhtar Sayyed
 * @since 1.0
 */
public interface BaseDao<T extends BaseDto, E extends BaseEntity> {

  /**
   * Convert dtoObject to entityObject.
   *
   * @param dto DTO object to convert from
   * @param entity Entity object to convert to
   */
  void transformDtoToEntity(final T dto, E entity);

  /**
   * Convert dtoObject to entityObject.
   *
   * @param entity Entity object to convert from
   * @param dto DTO object to convert dto
   */
  void transformEntityToDto(final E entity, T dto);
}
