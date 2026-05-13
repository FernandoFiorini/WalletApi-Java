package com.walletapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.walletapi.model.TransactionType;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {

}
