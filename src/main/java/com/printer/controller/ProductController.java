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

import com.printer.dto.ProductDTO;
import com.printer.dto.response.MessageResponseDTO;
import com.printer.enums.MessageSystem;
import com.printer.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/getProduct/{id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
		try {
			return this.productService.getProductById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(new ProductDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/getListProduct/{idOrg}")
	public ResponseEntity<List<ProductDTO>> getProductByOrg(@PathVariable Long idOrg) {
		try {
			return this.productService.getProductByOrg(idOrg);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/save")
	public ResponseEntity<MessageResponseDTO> save(@RequestBody ProductDTO product) {
		try {
			return this.productService.save(product);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new MessageResponseDTO(MessageSystem.ACTION_CARRIED_OUT.getString(), e.getLocalizedMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@DeleteMapping("/delete")
	ResponseEntity<MessageResponseDTO> delete(Long idProduct) {
		try {
			this.delete(idProduct);
			return new ResponseEntity<>(new MessageResponseDTO(MessageSystem.SUCCESS_ACTION.getString()),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new MessageResponseDTO(MessageSystem.REMOVE_REGISTER.getString(), e.getLocalizedMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}