package com.marwanmakm.boto.service.impl;

import com.marwanmakm.boto.dao.TransactionsDAO;
import com.marwanmakm.boto.dto.OperationResponseDto;
import com.marwanmakm.boto.service.DataLoaderService;
import java.io.IOException;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DataLoaderServiceImpl implements DataLoaderService {

  private final TransactionsDAO transactionsDAO;

  @Autowired
  public DataLoaderServiceImpl(TransactionsDAO transactionsDAO) {
    this.transactionsDAO = transactionsDAO;
  }

  @Override
  public OperationResponseDto load(MultipartFile file) throws IOException {
    // Map CSV File into Transactions List
    //    CSVEntityMapper csvEntityMapper = new CSVEntityMapper();
    //
    //    // Save the transactions in the DB
    //    List<Transaction> transactionList = csvEntityMapper.mapCSVtoTransactionEntity(file);
    //    transactionsDAO.saveAll(transactionList);

    return new OperationResponseDto(
        new Timestamp(System.currentTimeMillis()),
        "Información guardada",
        "Se ha guardado correctamente la información en la base de datos");
  }
}
