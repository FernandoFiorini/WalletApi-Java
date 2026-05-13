package com.walletapi.api;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walletapi.dto.UserTransactionDTO;
import com.walletapi.service.WalletTransactionService;

@RestController
@RequestMapping("/wallet-transactions")
public class WalletTransactionApi {

	private final WalletTransactionService walletTransactionService;

	public WalletTransactionApi(WalletTransactionService walletTransactionService) {
		this.walletTransactionService = walletTransactionService;
	}

	// Get all the transactions made: DEPOSIT, WITHDRAW and TRANSFERS
	@GetMapping("/listAllTransactions")
	public List<UserTransactionDTO> listAllTransactions() throws ParseException {
		return walletTransactionService.listAllTransactions();

	}

}
