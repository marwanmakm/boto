package com.marwanmakm.boto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Transaction {

  @Id @GeneratedValue Long id;
  String composedId;
  Date date;
  Time time;
  Float amount;
  String comment;

  @ManyToOne
  @JoinColumn(name = "id")
  Account acoount;

  @ManyToOne
  @JoinColumn(name = "id")
  OperationType operationType;

  @ManyToOne
  @JoinColumn(name = "id")
  CategoryTag categoryTag;
}
