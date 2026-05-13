package com.walletapi.model;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_wallettransaction")
public class WalletTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_wallettransaction")
	@SequenceGenerator(name = "gen_wallettransaction", sequenceName = "gen_wallettransaction", allocationSize = 1)
	@Column(name = "id_wallettransaction")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_wallet")
	private Wallet wallet;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_walletdestination")
	private Wallet walletDestination;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_transactiontype")
	private TransactionType transactionType;

	@Column(name = "vl_amount")
	private BigDecimal amount;

	@CreationTimestamp
	@Column(name = "dt_created_at")
	private Date createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Wallet getWalletDestination() {
		return walletDestination;
	}

	public void setWalletDestination(Wallet walletDestination) {
		this.walletDestination = walletDestination;
	}

}