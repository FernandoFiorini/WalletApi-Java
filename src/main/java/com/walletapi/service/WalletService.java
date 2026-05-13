package com.walletapi.service;

import com.walletapi.dto.WalletDTO;
import com.walletapi.dto.WalletTransactionDTO;

public interface WalletService {

	void createWallet(WalletDTO walletDTO);

	void deposit(WalletTransactionDTO walletTransactionDTO);

	void withdraw(WalletTransactionDTO walletTransactionDTO);

	void transfer(WalletTransactionDTO walletTransactionDTO);

}
