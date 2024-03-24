package com.marwanmakm.boto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories_tags")
public class CategoryTag {

  @Id String id;
  String name;
  String description;

  @ManyToOne
  @JoinColumn(name = "category_id")
  Category category;
}
