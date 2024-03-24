package com.marwanmakm.boto.dao;

import com.marwanmakm.boto.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesDAO extends JpaRepository<Category, String> {}
