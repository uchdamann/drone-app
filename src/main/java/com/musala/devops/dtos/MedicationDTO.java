package com.musala.devops.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
	@Pattern(regexp="^[a-zA-Z0-9_\\-]*$", message="Only -, _ and alphanumerics allowed")
	private String name;
	private Double weight;
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	@Pattern(regexp="^[A-Z0-9_]*$", message="Only _, uppercase letters and numbers allowed")
	private String code;
	private String image;
	
	public static MedicationDTOBuilder builder() {
		return new MedicationDTOBuilder();
	}
}
