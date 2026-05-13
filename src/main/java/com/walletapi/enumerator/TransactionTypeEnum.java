package com.walletapi.enumerator;

public enum TransactionTypeEnum {

	DEPOSIT(1L, "DEPOSIT"), WITHDRAW(2L, "WITHDRAW"), TRANSFER(3L, "TRANSFER IN"), REFUND(4L, "REFUND");

	private final Long id;
	private final String name;

	TransactionTypeEnum(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static TransactionTypeEnum getById(Long id) {

		for (TransactionTypeEnum transactionType : values()) {

			if (transactionType.getId().equals(id)) {
				return transactionType;
			}

		}

		return null;
	}

	public static TransactionTypeEnum getByName(String name) {

		for (TransactionTypeEnum transactionType : values()) {

			if (transactionType.getName().equalsIgnoreCase(name)) {
				return transactionType;
			}

		}

		return null;
	}

}