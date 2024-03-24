package com.marwanmakm.boto.dao;

import com.marwanmakm.boto.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrenciesDAO extends JpaRepository<Currency, String> {}
