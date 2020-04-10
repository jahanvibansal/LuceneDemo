package com.java.space.dto;

import com.java.space.dto.Pricing.PricingBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pricing{
	float rentPerSqFt;
	boolean electricityWaterExcluded;
	boolean priceNegotiable;
	float maintenancePerMonth;
	float bookingAmount;
	float annualDuesPayable;
}