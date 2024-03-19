package com.marwanmakm.boto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class OperationType {

  @Id String id;
  String name;
  String description;
}
