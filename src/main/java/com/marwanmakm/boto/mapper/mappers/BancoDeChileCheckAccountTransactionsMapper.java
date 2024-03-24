package com.marwanmakm.boto.mapper.mappers;

import com.marwanmakm.boto.mapper.AbstractTransactionsMapper;
import com.marwanmakm.boto.mapper.CSVFileTemplate;
import java.io.InputStream;

public class BancoDeChileCheckAccountTransactionsMapper extends AbstractTransactionsMapper {

  public BancoDeChileCheckAccountTransactionsMapper() {
    super("mapperId");
  }

  @Override
  public CSVFileTemplate map(InputStream inputStream) {
    return null;
  }
}
