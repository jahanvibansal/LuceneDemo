package com.java.space.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location{
	boolean hideHouseNumberFromBuyers;
	String houseNumber;
	String locality;
	String projectName;
	String state;
	String city;
	int pincode;
	String country;
}