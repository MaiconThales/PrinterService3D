package com.printer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	
	private Long id;
	private String name;
	private float width;
	private float length;
	private float height;
	private OrganizationDTO organization;

}