package com.walletapi.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class WalletTransactionDTO {

	private Long id;
	private Long walletId;
	private Long walletDestination;
	private BigDecimal amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWalletId() {
		return walletId;
	}

	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getWalletDestination() {
		return walletDestination;
	}

	public void setWalletDestination(Long walletDestination) {
		this.walletDestination = walletDestination;
	}

}
