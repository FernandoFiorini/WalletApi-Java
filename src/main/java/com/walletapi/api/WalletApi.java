package com.walletapi.api;

import java.text.ParseException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walletapi.dto.WalletDTO;
import com.walletapi.dto.WalletTransactionDTO;
import com.walletapi.service.WalletService;

@RestController
@RequestMapping("/wallets")
public class WalletApi {

	private final WalletService walletService;

	public WalletApi(WalletService walletService) {
		this.walletService = walletService;
	}

	@PostMapping("/createWallet")
	public void createWallet(@RequestBody WalletDTO walletDTO) throws ParseException {
		walletService.createWallet(walletDTO);

	}

	@PostMapping("/deposit")
	public void deposit(@RequestBody WalletTransactionDTO walletTransactionDTO) throws ParseException {
		walletService.deposit(walletTransactionDTO);

	}

	@PostMapping("/withdraw")
	public void withdraw(@RequestBody WalletTransactionDTO walletTransactionDTO) throws ParseException {
		walletService.withdraw(walletTransactionDTO);

	}

	@PostMapping("/transfer")
	public void transfer(@RequestBody WalletTransactionDTO walletTransactionDTO) throws ParseException {
		walletService.transfer(walletTransactionDTO);

	}

}
