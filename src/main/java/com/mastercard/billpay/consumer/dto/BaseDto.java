package com.mastercard.billpay.consumer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.Data;

/**
 * Base DTO is the super class of all DTO objects. All the request, responses in the service inherit
 * from this base class
 *
 * @author Mukhtar Sayyed
 * @since 1.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseDto implements Serializable {}
