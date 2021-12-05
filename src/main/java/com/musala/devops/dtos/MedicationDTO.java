package com.musala.devops.dtos;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@SuppressWarnings("deprecation")
public class MedicationDTO {
	private Long id;
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String name;
	private Double weight;
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String code;
	private String image;
	
	public static MedicationDTOBuilder builder() {
		return new MedicationDTOBuilder();
	}
}
