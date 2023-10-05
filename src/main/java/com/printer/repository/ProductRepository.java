package com.printer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.printer.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("SELECT p FROM Product p LEFT JOIN p.organization o WHERE o.id = :idOrg")
	List<Product> findByOrg(@Param("idOrg") Long idOrg);

}