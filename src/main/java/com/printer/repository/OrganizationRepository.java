package com.printer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.printer.model.Organization;


@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
	
	@Query("SELECT o FROM Organization o LEFT JOIN o.users u WHERE u.id = :userId")
	List<Organization> findByUsers(@Param("user") Long user);

}