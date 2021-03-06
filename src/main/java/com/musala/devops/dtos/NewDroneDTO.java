package com.musala.devops.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import com.musala.devops.enums.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@SuppressWarnings("deprecation")
public class NewDroneDTO {
	@NotBlank
	@Length(max = 100, min = 8)
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String serialNumber;
	@NotNull
	private Model model;
	@NotNull
	@Min(value = 0L)
	@Max(value = 100L)
	private Double batteryCapacity;
}