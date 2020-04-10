package com.java.space.dto;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Property {
	int id;
	User user;
	String title;
	String description;
	LocalDate availableFrom;
	Location location;
	int popularity;
	Purpose purpose;
	Type type;
	boolean multipleUnits;
	int noOfProperties;
	PropertyDetails details;
	Pricing pricing;
	List<File> pictures;
	List<String> amenities;
	Timings timing;
	Category category;
	public enum User{
		OWNER, DEALER, BUILDER
	}
	public enum Purpose{
		RENT,LEASE, SALE
	}
	public enum Type{
		ROOM, SEAT, GYM, COMMERCIAL
	}
	public enum Timings{
		HOURLY, DAILY, MONTHLY
	}
	public enum Category{
		PREMIUM, NORMAL
	}
}
