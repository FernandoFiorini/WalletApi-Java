package com.walletapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.walletapi.dto.UserTransactionDTO;
import com.walletapi.model.WalletTransaction;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {

	@Query("""
				SELECT new com.walletapi.dto.UserTransactionDTO(
					u.userName,
					tt.name,
					CASE
						WHEN tt.id IN (3, 4)
						THEN uReceived.userName
						ELSE 'N/A'
					END,
					wt.amount
				)
				FROM WalletTransaction wt
				INNER JOIN wt.wallet w
				INNER JOIN w.user u
				INNER JOIN wt.transactionType tt
				LEFT JOIN wt.walletDestination wReceived
				LEFT JOIN wReceived.user uReceived
			""")
	List<UserTransactionDTO> listAllTransactions();

}
