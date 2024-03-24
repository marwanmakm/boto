package com.marwanmakm.boto.mapper;

@FunctionalInterface
public interface TransactionsMapperFactory {

  AbstractTransactionsMapper create(String sourceType);
}
