package com.marwanmakm.boto.dao;

import com.marwanmakm.boto.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BanksDAO extends JpaRepository<Bank, String> {}
