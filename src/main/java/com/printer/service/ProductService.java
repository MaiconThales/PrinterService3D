package com.printer.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.printer.dto.ProductDTO;
import com.printer.dto.response.MessageResponseDTO;

public interface ProductService {
	
	/**
	 * List the product by ID.
	 * @param idProduct is the ID of the product you want to search for.
	 * @return Returns a product that was searched for by ID.
	 */
	ResponseEntity<ProductDTO> getProductById(Long idProduct);
	
	/**
	 * Lists a list of products from the organization.
	 * @param idOrg is the ID of the organization for which you want to list the products.
	 * @return Returns a list of products that are associated with an organization.
	 */
	ResponseEntity<List<ProductDTO>> getProductByOrg(Long idOrg);
	
	/**
	 * Used to save a new or edited object.
	 * @param product is the object that will be saved or edited.
	 * @return Returns a ResponseEntity with a message of what happened when the data was saved.
	 */
	ResponseEntity<MessageResponseDTO> save(ProductDTO product);
	
	/**
	 * Used to remove the object.
	 * @param idProduct is the id of the object you want to remove.
	 * @return Returns a ResponseEntity with a message of what happened when the data was deleted.
	 */
	void delete(Long idProduct);

}