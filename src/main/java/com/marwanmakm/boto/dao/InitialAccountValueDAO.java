package com.marwanmakm.boto.dao;

import com.marwanmakm.boto.entity.InitialAccountValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitialAccountValueDAO extends JpaRepository<InitialAccountValue, Long> {}
