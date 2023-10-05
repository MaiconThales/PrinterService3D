package com.printer.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDTO {
	
	private Long id;
	private String name;
    private Set<ProductDTO> products;
    private Set<UserDTO> users;

}