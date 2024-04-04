package com.marwanmakm.boto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {

  @Id String id;

  Date date;
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
  Tag tag;
}
