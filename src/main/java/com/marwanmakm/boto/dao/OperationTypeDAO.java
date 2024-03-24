package com.marwanmakm.boto.dao;

import com.marwanmakm.boto.entity.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationTypeDAO extends JpaRepository<OperationType, String> {}
