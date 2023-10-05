package com.printer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponseDTO {
	
	private String message;
	
	private String erroDescription;
	
	public MessageResponseDTO(String message) {
		this.message = message;
	}

}