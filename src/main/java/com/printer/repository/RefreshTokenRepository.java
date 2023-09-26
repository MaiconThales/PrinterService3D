package com.printer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.printer.model.RefreshToken;
import com.printer.model.User;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	
	Optional<RefreshToken> findByToken(String token);

	@Query(value = "SELECT * FROM refreshtoken WHERE user_id = :idUser", nativeQuery = true)
	Optional<RefreshToken> findByUserId(@Param("idUser") long idUser);

	@Modifying
	int deleteByUser(User user);

}