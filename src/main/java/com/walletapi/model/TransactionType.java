package com.walletapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_transactiontype")
public class TransactionType {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_transactiontype")
	@SequenceGenerator(name = "gen_transactiontype", sequenceName = "gen_transactiontype", allocationSize = 1)
	@Column(name = "id_transactiontype")
	private Long id;

	@Column(name = "tx_name")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}