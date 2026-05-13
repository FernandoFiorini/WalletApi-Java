package com.walletapi.service.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.walletapi.dto.WalletDTO;
import com.walletapi.dto.WalletTransactionDTO;
import com.walletapi.enumerator.TransactionTypeEnum;
import com.walletapi.model.User;
import com.walletapi.model.Wallet;
import com.walletapi.repository.UserRepository;
import com.walletapi.repository.WalletRepository;
import com.walletapi.service.WalletService;
import com.walletapi.service.WalletTransactionService;

import jakarta.transaction.Transactional;

@Service
public class WalletServiceImpl implements WalletService {

	private static final Logger logger = LoggerFactory.getLogger(WalletServiceImpl.class);

	private final WalletRepository walletRepository;
	private final UserRepository userRepository;
	private final WalletTransactionService walletTransactionService;

	public WalletServiceImpl(WalletRepository walletRepository, UserRepository userRepository,
			WalletTransactionService walletTransactionService) {
		this.walletRepository = walletRepository;
		this.userRepository = userRepository;
		this.walletTransactionService = walletTransactionService;
	}

	@Override
	public void createWallet(WalletDTO walletDTO) {
		logger.info("==> Executing createWallet");

		Wallet wallet = null;

		Optional<Wallet> walletOptional = walletRepository.findByUserId(walletDTO.getUserId());

		if (walletOptional.isPresent()) {
			throw new RuntimeException("Wallet already created for the user");
		}

		Optional<User> user = userRepository.findById(walletDTO.getUserId());

		if (user.isEmpty()) {
			throw new RuntimeException("User not found");
		}

		wallet = new Wallet();
		wallet.setBalance(BigDecimal.ZERO);
		wallet.setUser(user.get());

		walletRepository.save(wallet);

	}

	@Override
	@Transactional
	public void deposit(WalletTransactionDTO walletTransactionDTO) {
		logger.info("==> Executing deposit");

		this.validateWalletTransaction(walletTransactionDTO);

		Wallet wallet = walletRepository.findByIdWithLock(walletTransactionDTO.getWalletId())
				.orElseThrow(() -> new RuntimeException("Wallet not found"));

		wallet.setBalance(wallet.getBalance().add(walletTransactionDTO.getAmount()));

		walletRepository.save(wallet);

		// Saves the transaction
		walletTransactionService.save(walletTransactionDTO, wallet, null, TransactionTypeEnum.DEPOSIT.getId());

	}

	private void validateWalletTransaction(WalletTransactionDTO walletTransactionDTO) {

		if (walletTransactionDTO.getAmount() == null
				|| walletTransactionDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
			throw new RuntimeException("Deposit needs to be greater than 0");
		}

		if (walletTransactionDTO.getWalletId() == null) {
			throw new RuntimeException("Invalid walletId");
		}
	}

	@Override
	@Transactional
	public void withdraw(WalletTransactionDTO walletTransactionDTO) {
		logger.info("==> Executing withdraw");

		this.validateWalletTransaction(walletTransactionDTO);

		Wallet wallet = walletRepository.findByIdWithLock(walletTransactionDTO.getWalletId())
				.orElseThrow(() -> new RuntimeException("Wallet not found"));

		BigDecimal withdrawBalance = wallet.getBalance().subtract(walletTransactionDTO.getAmount());

		if (withdrawBalance.compareTo(BigDecimal.ZERO) <= 0) {
			throw new RuntimeException("Insufficient balance");
		}

		wallet.setBalance(wallet.getBalance().subtract(walletTransactionDTO.getAmount()));

		walletRepository.save(wallet);

		// Saves the transaction
		walletTransactionService.save(walletTransactionDTO, wallet, null, TransactionTypeEnum.WITHDRAW.getId());

	}

	@Override
	@Transactional
	public void transfer(WalletTransactionDTO walletTransactionDTO) {
		logger.info("==> Executing transfer");

		this.validateWalletTransaction(walletTransactionDTO);

		Wallet walletFrom = walletRepository.findByIdWithLock(walletTransactionDTO.getWalletId())
				.orElseThrow(() -> new RuntimeException("Wallet from not found"));

		Wallet walletDestination = walletRepository.findByIdWithLock(walletTransactionDTO.getWalletDestination())
				.orElseThrow(() -> new RuntimeException("Wallet destination not found"));

		BigDecimal transferBalance = walletFrom.getBalance().subtract(walletTransactionDTO.getAmount());

		if (transferBalance.compareTo(BigDecimal.ZERO) <= 0) {
			throw new RuntimeException("Insufficient balance");
		}

		walletFrom.setBalance(transferBalance);
		walletDestination.setBalance(walletDestination.getBalance().add(walletTransactionDTO.getAmount()));

		// Saves from
		walletRepository.save(walletFrom);

		// Saves destination
		walletRepository.save(walletDestination);

		// Saves the transactions
		walletTransactionService.save(walletTransactionDTO, walletFrom, walletDestination,
				TransactionTypeEnum.TRANSFER.getId());
	}

}
