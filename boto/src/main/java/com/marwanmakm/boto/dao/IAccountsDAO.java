package com.marwanmakm.boto.dao;

import com.marwanmakm.boto.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountsDAO extends JpaRepository<Account, String> {}
