package com.manning.energy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "battery_events")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class BatteryEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;

	@Column(name = "chargin_source")
	private String chargingSource;

	@Column(name = "device_id")
	private String deviceId;

	@Column(name = "charging")
	private int charging;

	@Column(name = "current_capacity")
	private int currentCapacity;

	@Column(name = "inverter_state")
	private int inverterState;

	@Column(name = "moduleL_temp")
	private int moduleLTemp;

	@Column(name = "moduleR_temp")
	private int moduleRTemp;

	@Column(name = "soc_regulator")
	private double soCRegulator;

	@Column(name = "processor1_temp")
	private int processor1Temp;

	@Column(name = "processor2_temp")
	private int processor2Temp;

	@Column(name = "processor3_temp")
	private int processor3Temp;

	@Column(name = "processor4_temp")
	private int processor4Temp;
}
