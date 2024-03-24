package com.marwanmakm.boto.mapper;

import java.io.IOException;
import java.io.InputStream;
import lombok.Getter;

@Getter
public abstract class AbstractTransactionsMapper {

  protected final String mapperId;

  public AbstractTransactionsMapper(String mapperId) {
    this.mapperId = mapperId;
  }

  public abstract CSVFileTemplate map(InputStream inputStream) throws IOException;
}
