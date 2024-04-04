package com.marwanmakm.boto.dao;

import com.marwanmakm.boto.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsDAO extends JpaRepository<Tag, String> {}
