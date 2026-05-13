package com.walletapi.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.walletapi.dto.UserTransactionDTO;
import com.walletapi.dto.WalletTransactionDTO;
import com.walletapi.model.TransactionType;
import com.walletapi.model.Wallet;
import com.walletapi.model.WalletTransaction;
import com.walletapi.repository.TransactionTypeRepository;
import com.walletapi.repository.WalletTransactionRepository;
import com.walletapi.service.WalletTransactionService;

import jakarta.transaction.Transactional;

@Service
public class WalletTransactionServiceImpl implements WalletTransactionService {

	private static final Logger logger = LoggerFactory.getLogger(WalletServiceImpl.class);

	private final WalletTransactionRepository walletTransactionRepository;
	private final TransactionTypeRepository transactionTypeRepository;

	public WalletTransactionServiceImpl(WalletTransactionRepository walletTransactionRepository,
			TransactionTypeRepository transactionTypeRepository) {
		this.walletTransactionRepository = walletTransactionRepository;
		this.transactionTypeRepository = transactionTypeRepository;
	}

	@Override
	@Transactional
	public void save(WalletTransactionDTO walletTransactionDTO, Wallet wallet, Wallet walletDestination,
			Long transactionTypeCode) {
		logger.info("==> Executing save");

		TransactionType transactionType = transactionTypeRepository.findById(transactionTypeCode)
				.orElseThrow(() -> new RuntimeException("Transaction type not found"));

		WalletTransaction walletTransaction = new WalletTransaction();

		walletTransaction.setAmount(walletTransactionDTO.getAmount());
		walletTransaction.setCreatedAt(new Date());
		walletTransaction.setTransactionType(transactionType);
		walletTransaction.setWallet(wallet);

		if (walletDestination != null) {
			walletTransaction.setWalletDestination(walletDestination);
		}

		walletTransactionRepository.save(walletTransaction);

	}

	@Override
	public List<UserTransactionDTO> listAllTransactions() {
		logger.info("==> Executing listAllTransactions");

		return walletTransactionRepository.listAllTransactions();

	}

}
