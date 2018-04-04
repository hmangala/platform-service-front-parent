/**
 * Copyright (C) Davinta Technologies 2017. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Davinta Technologies. You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms and conditions
 * entered into with Davinta Technologies.
 */

package com.davinta.aeus.service.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.davinta.aeus.integration.kafka.KafkaProducerConfiguration;
import com.davinta.aeus.messaging.customer.FrontRequest;
import com.davinta.aeus.util.logging.PlatformLogger;
import com.davinta.aeus.util.messaging.PlatformBaseController;

/**
 * The Class FrontController.
 * @author Harish Mangala
 */
@Controller
public class FrontController extends PlatformBaseController {

	PlatformLogger logger = PlatformLogger.getLogger(getClass());

	@Autowired
	private KafkaProducerConfiguration kafkaProducerConfiguration;

	@Autowired
	KafkaMessageProducer kafkaMessageProducer;

	@MessageMapping("/frontRequest")
	public void frontRequest(FrontRequest frontRequest) {
		kafkaMessageProducer.sendDefault(frontRequest, kafkaProducerConfiguration.getTopicSend(), 100);
	}
}
