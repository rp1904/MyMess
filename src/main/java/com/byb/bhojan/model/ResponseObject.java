package com.byb.bhojan.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ResponseObject {

	private ResponseStatus responseType;
	private List<CustomeFieldError> customeFieldErrors = new ArrayList<CustomeFieldError>();
	private String message;

	@SuppressWarnings("rawtypes")
	public ResponseEntity getResponse(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			for (FieldError e : errors) {
				System.out.println("Errrrrrrrrrrrr " + e.getField() + " --- " + e.getCode());
				this.customeFieldErrors.add(new CustomeFieldError(e.getField(), e.getCode()));
			}

			this.responseType = ResponseStatus.Error;
			return ResponseEntity.badRequest().body(this);
		}

		this.responseType = ResponseStatus.Success;
		this.customeFieldErrors = null;
		return ResponseEntity.ok(this);
	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity getSuccessResponse(String message) {

		this.responseType = ResponseStatus.Success;
		this.customeFieldErrors = null;
		this.message = message;

		return ResponseEntity.ok(this);
	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity getErrorResponse(String message) {

		this.responseType = ResponseStatus.Error;
		this.customeFieldErrors = null;
		this.message = message;

		return ResponseEntity.ok(this);
	}

	@SuppressWarnings("rawtypes")
	public ResponseEntity getAlertResponse(String message) {

		this.responseType = ResponseStatus.Alert;
		this.customeFieldErrors = null;
		this.message = message;

		return ResponseEntity.ok(this);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseStatus getResponseType() {
		return responseType;
	}

	public void setResponseType(ResponseStatus responseType) {
		this.responseType = responseType;
	}

	public List<CustomeFieldError> getCustomeFieldErrors() {
		return customeFieldErrors;
	}

	public void setCustomeFieldErrors(List<CustomeFieldError> customeFieldErrors) {
		this.customeFieldErrors = customeFieldErrors;
	}

}
