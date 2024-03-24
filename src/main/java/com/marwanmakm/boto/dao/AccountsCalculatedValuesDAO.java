package com.marwanmakm.boto.dao;

import com.marwanmakm.boto.entity.AccountCalculatedValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsCalculatedValuesDAO
    extends JpaRepository<AccountCalculatedValue, String> {}
