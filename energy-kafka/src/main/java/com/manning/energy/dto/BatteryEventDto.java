package com.manning.energy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BatteryEventDto {

	@JsonProperty("charging_source")
	private String chargingSource;

	@JsonProperty("device_id")
	private String deviceId;

	private int charging;

	@JsonProperty("current_capacity")
	private int currentCapacity;

	@JsonProperty("inverter_state")
	private int inverterState;

	@JsonProperty("moduleL_temp")
	private int moduleLTemp;

	@JsonProperty("moduleR_temp")
	private int moduleRTemp;

	@JsonProperty("SoC_regulator")
	private double soCRegulator;

	@JsonProperty("processor1_temp")
	private int processor1Temp;

	@JsonProperty("processor2_temp")
	private int processor2Temp;

	@JsonProperty("processor3_temp")
	private int processor3Temp;

	@JsonProperty("processor4_temp")
	private int processor4Temp;
}
