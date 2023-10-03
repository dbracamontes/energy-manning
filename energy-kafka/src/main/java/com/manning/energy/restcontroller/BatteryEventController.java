package com.manning.energy.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.manning.energy.avro.model.BatteryEventAvro;
import com.manning.energy.converter.GenericConverter;
import com.manning.energy.service.BatteryEventKafkaService;
import com.manning.energy.service.BatteryEventService;
import com.manning.energy.dto.BatteryEventDto;

@RestController
@RequestMapping(path = "/api/batteryevent")
public class BatteryEventController {

	@Autowired
	private BatteryEventKafkaService batteryEventKafkaService;

	@Autowired
	private BatteryEventService batteryEventService;

	@Autowired
	private GenericConverter<BatteryEventAvro, BatteryEventDto> converter;

	@PostMapping(value = "/device/{deviceId}", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public void generateBatteryEvent(@PathVariable("deviceId") String deviceId,
			@RequestBody List<BatteryEventDto> batteryEventList) {
		for (BatteryEventDto batteryEvent : batteryEventList) {
			BatteryEventAvro batteryEventAvro = converter.apply(new BatteryEventAvro(), batteryEvent);
			batteryEventKafkaService.sendMessage(batteryEventAvro, deviceId);
		}
	}

	@GetMapping(value = "/device/{deviceId}")
	public List<BatteryEventDto> getBatteryEventsByDeviceId(@PathVariable("deviceId") String deviceId) {
		return batteryEventService.getBatteryEventsByDeviceId(deviceId);
	}
}
