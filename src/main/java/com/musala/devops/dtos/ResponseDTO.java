package com.musala.devops.dtos;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResponseDTO<T> {
	private final int responseCode;
	private final String message;
	private final T data;
	
	private ResponseDTO(int rCode, String message, T data) {
		this.responseCode=rCode;
		this.message=message;
		this.data=data;
	}
	
	public static <T> ResponseDTO<T> newInstance(int rCode, String message, T data){
		return new ResponseDTO<T>(rCode, message, data);
	}
}
