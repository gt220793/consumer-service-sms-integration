package com.mastercard.billpay.consumer.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * This class is responsible for reading external config from application.yml or commandline args
 *
 * @author Mukhtar Sayyed
 * @since 1.0
 */
@Component
@ConfigurationProperties(prefix = "app.bpx")
public class BpxProperties {}
