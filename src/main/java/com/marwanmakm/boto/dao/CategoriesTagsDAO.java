package com.marwanmakm.boto.dao;

import com.marwanmakm.boto.entity.CategoryTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesTagsDAO extends JpaRepository<CategoryTag, String> {}
