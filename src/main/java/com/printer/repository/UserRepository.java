package com.printer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.printer.dto.UserDTO;
import com.printer.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	@Query(value = "SELECT language FROM user WHERE id = :idUser", nativeQuery = true)
	String getLanguageUser(@Param("idUser") Long idUser);

	@Query(value = "SELECT new com.printer.dto.UserDTO(username, email, phone, language) FROM com.printer.model.User WHERE id = :idUser")
	UserDTO listUserById(long idUser);

	@Query(value = "SELECT id FROM user WHERE username = :username", nativeQuery = true)
	long getIdUserByUsername(@Param("username") String username);

}