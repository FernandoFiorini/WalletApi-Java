package com.walletapi.dto;

import java.math.BigDecimal;

public class UserTransactionDTO {

	private String userFrom;
	private String transaction;
	private String userReceived;
	private BigDecimal amount;

	public UserTransactionDTO(String userFrom, String transaction, String userReceived, BigDecimal amount) {
		super();
		this.userFrom = userFrom;
		this.transaction = transaction;
		this.userReceived = userReceived;
		this.amount = amount;
	}

	public String getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(String userFrom) {
		this.userFrom = userFrom;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public String getUserReceived() {
		return userReceived;
	}

	public void setUserReceived(String userReceived) {
		this.userReceived = userReceived;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
