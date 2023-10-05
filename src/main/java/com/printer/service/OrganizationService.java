package com.printer.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.printer.dto.OrganizationDTO;
import com.printer.dto.response.MessageResponseDTO;

public interface OrganizationService {
	
	/**
	 * Used to return an organization from the organization ID.
	 * @param idOrg is the ID of the organization that you want to retrieve the data from.
	 * @return Returns a ResponseEntity with Organization data.
	 */
	ResponseEntity<OrganizationDTO> getOrganizationById(Long idOrg);
	
	/**
	 * Used to return a list of organizations from the user.
	 * @param idUser is the user ID that you want to list all your organizations.
	 * @return Returns a ResponseEntity with a list of Organization.
	 */
	ResponseEntity<List<OrganizationDTO>> getListOrganizationByUser(Long idUser);
	
	/**
	 * Used to save a new entity or edit an existing one.
	 * @param org is the object with the information you want to save or change.
	 * @return Returns a ResponseEntity with a message of what happened when the data was saved.
	 */
	ResponseEntity<MessageResponseDTO> save(OrganizationDTO org);
	
	/**
	 * Used to remove an organization.
	 * @param idOrg is the ID of the organization you want to delete.
	 * @return Returns a ResponseEntity with a message of what happened when the data was deleted.
	 */
	void delete(Long idOrg);

}