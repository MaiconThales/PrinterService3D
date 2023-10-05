package com.printer.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.printer.dto.ProductDTO;
import com.printer.dto.response.MessageResponseDTO;
import com.printer.enums.MessageSystem;
import com.printer.model.Product;
import com.printer.repository.ProductRepository;
import com.printer.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ProductRepository productRepository;

	@Override
	public ResponseEntity<ProductDTO> getProductById(Long idProduct) {
		ProductDTO pMapper = this.modelMapper.map(this.productRepository.getReferenceById(idProduct), ProductDTO.class);
		return new ResponseEntity<>(pMapper, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ProductDTO>> getProductByOrg(Long idOrg) {
		List<Product> result = this.productRepository.findByOrg(idOrg);
		List<ProductDTO> resultDTO = result.stream().map(org -> this.modelMapper.map(org, ProductDTO.class)).toList();
		return new ResponseEntity<>(resultDTO, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<MessageResponseDTO> save(ProductDTO product) {
		this.productRepository.save(this.modelMapper.map(product, Product.class));
		return new ResponseEntity<>(new MessageResponseDTO(MessageSystem.ACTION_CARRIED_OUT.getString()), HttpStatus.OK);
	}

	@Override
	public void delete(Long idProduct) {
		this.productRepository.delete(new Product(idProduct));
	}

}