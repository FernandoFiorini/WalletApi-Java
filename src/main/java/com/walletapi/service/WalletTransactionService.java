package com.walletapi.service;

import java.util.List;

import com.walletapi.dto.UserTransactionDTO;
import com.walletapi.dto.WalletTransactionDTO;
import com.walletapi.model.Wallet;

public interface WalletTransactionService {

	void save(WalletTransactionDTO walletTransactionDTO, Wallet wallet, Wallet walletDestination, Long transactionType);

	List<UserTransactionDTO> listAllTransactions();

}
