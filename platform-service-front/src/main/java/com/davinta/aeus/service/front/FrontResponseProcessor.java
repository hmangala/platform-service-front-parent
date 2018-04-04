package com.davinta.aeus.service.front;

import org.springframework.stereotype.Component;

import com.davinta.aeus.messaging.customer.FrontResponse;
import com.davinta.aeus.util.kafka.MessageContext;

@Component("frontResponseProcessor")
public class FrontResponseProcessor extends WebsocketResponseSender<FrontResponse> {

	@Override
	public Class<FrontResponse> getResponseClass() {
		return FrontResponse.class;
	}

	@Override
	public void processResponse(MessageContext<FrontResponse> context) {
		sendTo("/topic/client1", context.getMessage());
	}

}
