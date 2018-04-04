package com.davinta.aeus.service.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.davinta.aeus.messaging.customer.FrontResponse;
import com.davinta.aeus.util.kafka.ResponseProcessor;

public abstract class WebsocketResponseSender<T> implements ResponseProcessor<T> {
	@Autowired
	SimpMessagingTemplate template;

	public void sendTo(String topic, FrontResponse frontResponse) {
		template.convertAndSend(topic, frontResponse);
	}
}
