package com.printer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.printer.enums.ERole;
import com.printer.enums.MessageSystem;
import com.printer.model.Role;
import com.printer.repository.RoleRepository;
import com.printer.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findByName(ERole name) {
		return this.roleRepository.findByName(name)
				.orElseThrow(() -> new RuntimeException(MessageSystem.ROLENOTFOUND.getString()));
	}

}