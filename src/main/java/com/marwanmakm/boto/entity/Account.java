package com.marwanmakm.boto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Account {
  @Id String id;
  String name;
  String description;

  @ManyToOne
  @JoinColumn(name = "id")
  Bank bank;

  @ManyToOne
  @JoinColumn(name = "id")
  AccountType accountType;

  @ManyToOne
  @JoinColumn(name = "id")
  Currency currency;
}
