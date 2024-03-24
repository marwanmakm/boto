package com.marwanmakm.boto.mapper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum TransactionsMapper {
  BDC_TDC("bdc_tdc"),
  BDC_CHECK("bcd_check");

  private final String id;

  TransactionsMapper(String id) {
    this.id = id;
  }

  private static final Map<String, TransactionsMapper> ENUM_MAP = new ConcurrentHashMap<>();

  static {
    for (TransactionsMapper v : TransactionsMapper.values()) {
      ENUM_MAP.put(v.id, v);
    }
  }

  public static TransactionsMapper findById(String id) {
    return ENUM_MAP.get(id);
  }
}
