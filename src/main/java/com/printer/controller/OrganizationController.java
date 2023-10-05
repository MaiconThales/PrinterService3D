package com.printer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.printer.dto.OrganizationDTO;
import com.printer.dto.response.MessageResponseDTO;
import com.printer.enums.MessageSystem;
import com.printer.service.OrganizationService;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/getOrg/{id}")
	public ResponseEntity<OrganizationDTO> getOrganizationById(@PathVariable Long id) {
		try {
			return this.organizationService.getOrganizationById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(new OrganizationDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/getListOrg/{idUser}")
	public ResponseEntity<List<OrganizationDTO>> getListOrganizationByUser(@PathVariable Long idUser) {
		try {
			return this.organizationService.getListOrganizationByUser(idUser);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/save")
	public ResponseEntity<MessageResponseDTO> save(@RequestBody OrganizationDTO org) {
		try {
			return this.organizationService.save(org);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new MessageResponseDTO(MessageSystem.ACTION_CARRIED_OUT.getString(), e.getLocalizedMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@DeleteMapping("/delete")
	ResponseEntity<MessageResponseDTO> delete(Long idOrg) {
		try {
			this.delete(idOrg);
			return new ResponseEntity<>(new MessageResponseDTO(MessageSystem.SUCCESS_ACTION.getString()),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new MessageResponseDTO(MessageSystem.REMOVE_REGISTER.getString(), e.getLocalizedMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}