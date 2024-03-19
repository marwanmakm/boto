package com.marwanmakm.boto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CategoryTag {

  @Id String id;
  String name;
  String description;

  @ManyToOne
  @JoinColumn(name = "id")
  Category category;
}
