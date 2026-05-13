package com.walletapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.walletapi.model.Wallet;

import jakarta.persistence.LockModeType;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("""
			    SELECT c
			    FROM Wallet c
			    WHERE c.id = :walletId
			""")
	Optional<Wallet> findByIdWithLock(@Param("walletId") Long walletId);

	@Query("""
			    SELECT c
			    FROM Wallet c
			    WHERE c.user.id = :userId
			""")
	Optional<Wallet> findByUserId(@Param("userId") Long userId);

}
