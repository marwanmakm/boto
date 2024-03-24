package com.marwanmakm.boto.entity;

import java.util.Locale;
import lombok.Getter;

@Getter
public enum OperationType {
  INCOME("income"),
  OUTCOME("outcome");

  private final String id;

  OperationType(String id) {
    this.id = id;
  }

  public static OperationType findById(String id) {
    return valueOf(id.toUpperCase(Locale.ROOT));
  }
}
