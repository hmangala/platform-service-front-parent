package com.davinta.aeus.service.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.ListenableFutureTask;
import org.springframework.web.context.request.async.DeferredResult;

import com.davinta.aeus.util.kafka.MessageProducer;

@Component
public class KafkaMessageProducer extends MessageProducer {

	@Autowired
	MessageChannel toKafka;

	@Override
	protected void send(Message<?> message) {
		toKafka.send(message);
	}

	public <T, R> DeferredResult<ResponseEntity<R>> register(T message, String topicSend, Integer messageType, String correlationId, Class<R> responseClass) {
		final DeferredResult<ResponseEntity<R>> deferredResult = new DeferredResult<>();
		ListenableFutureTask<R> futureTask = subscribe(message, topicSend, messageType, correlationId, responseClass);
		futureTask.addCallback(new ListenableFutureCallback<R>() {
			@Override
			public void onSuccess(R result) {
				deferredResult.setResult(ResponseEntity.ok(result));
			}

			@Override
			public void onFailure(Throwable t) {
				deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(t));
			}
		});
		return deferredResult;
	}

}
