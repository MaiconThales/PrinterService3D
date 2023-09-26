package com.printer.service;

import com.printer.enums.ERole;
import com.printer.model.Role;

public interface RoleService {
	
	/**
	 * Finds the Role from the ENUM class.
	 * @param name is the ENUM class with the content to be searched.
	 * @return If the value is found a Role object will be returned, if it is not found an exception is thrown.
	 */
	Role findByName(ERole name);

}