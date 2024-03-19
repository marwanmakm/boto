package com.marwanmakm.boto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AccountType {

  @Id String id;
  String description;
}
