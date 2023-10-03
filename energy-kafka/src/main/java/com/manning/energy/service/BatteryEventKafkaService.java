package com.manning.energy.service;

import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import com.manning.energy.avro.model.BatteryEventAvro;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BatteryEventKafkaService {
	@Value(value = "${kafka.topic.name}")
	private String kafkaTopic;

	@Autowired
	public KafkaTemplate<String, BatteryEventAvro> kafkaTemplate;

	public void sendMessage(BatteryEventAvro batteryEvent, String deviceId) {
		CompletableFuture<SendResult<String, BatteryEventAvro>> completableFuture = kafkaTemplate.send(kafkaTopic, deviceId,
				batteryEvent);

		completableFuture.whenComplete((result, ex) -> {
			if (ex != null) {
				log.error("Unable to send message=[{}] due to : {}", batteryEvent, ex.getMessage());
			} else {
				log.info("Sent message=[{}] with offset=[{}]", batteryEvent, result.getRecordMetadata().offset());
			}
		});
	}

}
