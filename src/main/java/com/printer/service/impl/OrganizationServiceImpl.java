package com.printer.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.printer.dto.OrganizationDTO;
import com.printer.dto.response.MessageResponseDTO;
import com.printer.enums.MessageSystem;
import com.printer.model.Organization;
import com.printer.model.User;
import com.printer.repository.OrganizationRepository;
import com.printer.service.AuthService;
import com.printer.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private OrganizationRepository organizationRepository;
	@Autowired
	private AuthService authService;
	
	@Override
	public ResponseEntity<OrganizationDTO> getOrganizationById(Long idOrg) {
		OrganizationDTO oMapper = this.modelMapper.map(this.organizationRepository.getReferenceById(idOrg), OrganizationDTO.class);
		return new ResponseEntity<>(oMapper, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<OrganizationDTO>> getListOrganizationByUser(Long idUser) {
		List<Organization> result = this.organizationRepository.findByUsers(idUser);
		List<OrganizationDTO> resultDTO = result.stream().map(org -> this.modelMapper.map(org, OrganizationDTO.class)).toList();
		return new ResponseEntity<>(resultDTO, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<MessageResponseDTO> save(OrganizationDTO org) {
		Organization o = this.modelMapper.map(org, Organization.class);
		if(o.getUsers().isEmpty()) {
			Set<User> listUser = new HashSet<>();
			listUser.add(this.authService.getUserLogged());
			o.setUsers(listUser);
		}
		this.organizationRepository.save(o);
		return new ResponseEntity<>(new MessageResponseDTO(MessageSystem.ACTION_CARRIED_OUT.getString()), HttpStatus.OK);
	}

	@Override
	public void delete(Long idOrg) {
		this.organizationRepository.delete(new Organization(idOrg));
	}

}