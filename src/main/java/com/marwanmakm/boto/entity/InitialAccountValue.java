package com.marwanmakm.boto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "initial_account_values")
public class InitialAccountValue {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  Long id;

  @OneToOne
  @JoinColumn(name = "account_id")
  Account account;

  Double value;
  Date loadedDate;
}
