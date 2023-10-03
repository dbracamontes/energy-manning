package com.manning.energy.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.manning.energy.converter.GenericConverter;
import com.manning.energy.dto.BatteryEventDto;
import com.manning.energy.entity.BatteryEvent;
import com.manning.energy.repository.BatteryEventRep;

@Service
public class BatteryEventService {

	@Autowired
	private BatteryEventRep batteryEventRep;

	@Autowired
	private GenericConverter<BatteryEventDto, BatteryEvent> converter;

	public BatteryEvent save(BatteryEvent batteryEvent) {
		return batteryEventRep.save(batteryEvent);
	}

	public List<BatteryEventDto> getBatteryEventsByDeviceId(String deviceId) {
		List<BatteryEvent> batteryEventList = batteryEventRep.getBatteryEventsByDeviceId(deviceId);
		return batteryEventList.stream().map(batteryEvent -> converter.apply(new BatteryEventDto(), batteryEvent)).collect(Collectors.toList());
	}
}
