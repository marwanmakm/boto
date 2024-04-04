package com.marwanmakm.boto.mapper;

import static com.marwanmakm.boto.mapper.TransactionsMapper.BDC_CHECK;
import static com.marwanmakm.boto.mapper.TransactionsMapper.BDC_TDC;
import static com.marwanmakm.boto.mapper.TransactionsMapper.SANTANDER_CHECK;

import com.marwanmakm.boto.mapper.mappers.BancoDeChileCheckAccountCSVTransactionsMapper;
import com.marwanmakm.boto.mapper.mappers.BancoDeChileCreditCardTransactionsMapper;
import com.marwanmakm.boto.mapper.mappers.SantanderCheckAccountCSVTransactionsMapper;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class TransactionsMapperFactoryImpl implements TransactionsMapperFactory {

  private static final Map<TransactionsMapper, AbstractTransactionsMapper> transactionsMap;

  static {
    transactionsMap = new HashMap<>();
    transactionsMap.put(BDC_TDC, new BancoDeChileCreditCardTransactionsMapper());
    transactionsMap.put(BDC_CHECK, new BancoDeChileCheckAccountCSVTransactionsMapper());
    transactionsMap.put(SANTANDER_CHECK, new SantanderCheckAccountCSVTransactionsMapper());
  }

  // TODO: Acomodar esta excepcion
  @Override
  public AbstractTransactionsMapper create(String sourceType) {
    try {
      return transactionsMap.get(TransactionsMapper.findById(sourceType));
    } catch (NullPointerException e) {
      throw new IllegalStateException("No se ha encontrado el tipo de fuente.");
    }
  }
}
