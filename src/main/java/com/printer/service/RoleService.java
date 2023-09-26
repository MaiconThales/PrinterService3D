package com.printer.service;

import com.printer.enums.ERole;
import com.printer.model.Role;

public interface RoleService {
	
	Role findByName(ERole name);

}