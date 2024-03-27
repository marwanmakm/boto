package com.marwanmakm.boto.mapper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum TransactionsMapper {
  BDC_TDC("bdc_tdc"),
  BDC_CHECK("bdc_check"),
  BDC_CHECK_CSV("bdc_check_csv");

  private static final Map<String, TransactionsMapper> ENUM_MAP = new ConcurrentHashMap<>();

  static {
    for (TransactionsMapper v : TransactionsMapper.values()) {
      ENUM_MAP.put(v.id, v);
    }
  }

  private final String id;

  TransactionsMapper(String id) {
    this.id = id;
  }

  public static TransactionsMapper findById(String id) {
    return ENUM_MAP.get(id);
  }
}
