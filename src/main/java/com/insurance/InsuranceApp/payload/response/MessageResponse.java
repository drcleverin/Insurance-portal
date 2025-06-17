package com.insurance.InsuranceApp.payload.response;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
    private String message;

	public String getMessage() {
		return message;
	}

	public MessageResponse(String message) {
		super();
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
