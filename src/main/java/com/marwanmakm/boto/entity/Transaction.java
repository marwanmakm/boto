package com.marwanmakm.boto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import java.sql.Time;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {

  @Id @GeneratedValue Long id;
  String composedId;
  Date date;
  Time time;
  Double amount;
  String comment;

  @ManyToOne
  @JoinColumn(name = "account_id")
  Account account;

  @ManyToOne
  @JoinColumn(name = "opertation_type_id")
  OperationType operationType;

  @ManyToOne
  @JoinColumn(name = "category_tag_id")
  CategoryTag categoryTag;
}
