package com.marwanmakm.boto.dao;

import com.marwanmakm.boto.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsDAO extends JpaRepository<Transaction, Long> {}
