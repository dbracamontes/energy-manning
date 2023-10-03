package com.manning.energy.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.manning.energy.avro.model.BatteryEventAvro;
import com.manning.energy.converter.GenericConverter;
import com.manning.energy.entity.BatteryEvent;
import com.manning.energy.service.BatteryEventService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BatteryEventConsumer {

	@Autowired
	public BatteryEventService batteryEventService;

	@Autowired
	private GenericConverter<BatteryEvent, BatteryEventAvro> converter;

	@KafkaListener(topics = "#{'${kafka.topic.name}'}", groupId = "#{'${kafka.consumer.group-id}'}")
	public void listen(BatteryEventAvro message) {
		BatteryEvent batteryEvent = new BatteryEvent();
		converter.apply(batteryEvent, message);

		log.info("Received Messasge in group : {}", batteryEvent);
		batteryEventService.save(batteryEvent);
	}
}
