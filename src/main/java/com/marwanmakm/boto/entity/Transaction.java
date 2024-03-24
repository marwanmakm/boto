package com.marwanmakm.boto.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  Long id;

  String composedId;
  Date date;
  @Nullable Time time;
  Double amount;
  String comment;

  @ManyToOne
  @JoinColumn(name = "account_id")
  Account account;

  @Enumerated(EnumType.STRING) // Map the Java enum to a String
  @Column(name = "operation_type")
  OperationType operationType;

  @ManyToOne
  @JoinColumn(name = "category_tag_id")
  CategoryTag categoryTag;
}
