package com.marwanmakm.boto.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tags")
public class Tag {

  @Id String id;
  String name;
  @Nullable String description;
}
