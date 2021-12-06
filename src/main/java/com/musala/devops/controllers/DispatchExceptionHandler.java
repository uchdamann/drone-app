package com.musala.devops.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.UnexpectedTypeException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.musala.devops.dtos.ResponseDTO;
import com.musala.devops.exceptions.DroneDetailsException;
import com.musala.devops.exceptions.InvalidSerialNumberException;
import com.musala.devops.exceptions.LoadWeightRestrictionException;
import com.musala.devops.exceptions.LowBatteryException;

import static com.musala.devops.enums.ResponseMessages.*;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DispatchExceptionHandler {

	@ExceptionHandler(DroneDetailsException.class)
	public ResponseDTO<String> handleDroneDetailsException(DroneDetailsException ex) {
		return ResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), ex.getMessage());
	}

	@ExceptionHandler(InvalidSerialNumberException.class)
	public ResponseDTO<String> handleInvalidSerialNumberException(InvalidSerialNumberException ex) {
		return ResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), ex.getMessage());
	}

	@ExceptionHandler(LoadWeightRestrictionException.class)
	public ResponseDTO<String> handleLoadWeightRestrictionException(LoadWeightRestrictionException ex) {
		return ResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), ex.getMessage());
	}
	
	@ExceptionHandler(LowBatteryException.class)
	public ResponseDTO<String> handleLowBatteryException(LowBatteryException ex) {
		return ResponseDTO.newInstance(LOW_BATTERY.getCode(), LOW_BATTERY.getMessage(), ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseDTO<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return ResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), errors);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseDTO<Map<String, String>> handleHttpExceptions(HttpClientErrorException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("error2", ex.getMessage());

		return ResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), errors);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseDTO<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
		String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
		return ResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), error);
	}

	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseDTO<String> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append("method is not supported for this request. Supported methods are ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
		return ResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), builder.toString());
	}

	@ExceptionHandler(UnexpectedTypeException.class)
	public ResponseDTO<String> handleUnexpectedTypeException(UnexpectedTypeException ex) {
		return ResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), ex.getMessage());
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseDTO<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		return ResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), ex.getMessage());
	}
}