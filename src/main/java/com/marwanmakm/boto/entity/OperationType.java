package com.marwanmakm.boto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "operations_types")
public class OperationType {

  @Id String id;
  String name;
  String description;
}
