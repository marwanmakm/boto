package com.marwanmakm.boto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "account_calculated_values")
public class AccountCalculatedValue {
  @Id Long id;
  Date date;
  Float amount;

  @ManyToOne
  @JoinColumn(name = "account_id")
  Account account;
}
