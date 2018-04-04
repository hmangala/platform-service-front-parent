/**
 * Copyright (C) Davinta Technologies 2017. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Davinta Technologies. You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms and conditions
 * entered into with Davinta Technologies.
 */

package com.davinta.aeus.service.front;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.davinta.aeus.integration.kafka.KafkaProducerConfiguration;
import com.davinta.aeus.messaging.customer.FrontRequest;
import com.davinta.aeus.messaging.customer.FrontResponse;
import com.davinta.aeus.util.logging.PlatformLogger;
import com.davinta.aeus.util.messaging.PlatformBaseController;

/**
 * The Class FrontController.
 * @author Harish Mangala
 */
@RestController
public class FrontRestController extends PlatformBaseController {

	PlatformLogger logger = PlatformLogger.getLogger(getClass());

	@Autowired
	private KafkaProducerConfiguration kafkaProducerConfiguration;

	@Autowired
	KafkaMessageProducer kafkaMessageProducer;

	@RequestMapping(value = "/frontRestRequest", method = RequestMethod.POST)
	public DeferredResult<ResponseEntity<FrontResponse>> frontRestRequest(FrontRequest frontRequest) {
		return kafkaMessageProducer.register(frontRequest, kafkaProducerConfiguration.getTopicSend(), 100, UUID.randomUUID().toString(), FrontResponse.class);
	}
}
