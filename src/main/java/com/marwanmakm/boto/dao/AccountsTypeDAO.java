package com.marwanmakm.boto.dao;

import com.marwanmakm.boto.entity.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsTypeDAO extends JpaRepository<AccountType, String> {}
