package com.manning.energy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.manning.energy.entity.BatteryEvent;

@Repository
public interface BatteryEventRep extends JpaRepository<BatteryEvent,Long> {

	public List<BatteryEvent> getBatteryEventsByDeviceId(String deviceId);
}
