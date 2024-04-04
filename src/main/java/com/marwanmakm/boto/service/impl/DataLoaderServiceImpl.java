package com.marwanmakm.boto.service.impl;

import static com.marwanmakm.boto.mapper.CSVFileTemplate.HEADERS;

import com.marwanmakm.boto.dao.AccountsDAO;
import com.marwanmakm.boto.dao.TagsDAO;
import com.marwanmakm.boto.dao.TransactionsDAO;
import com.marwanmakm.boto.dto.OperationResponseDto;
import com.marwanmakm.boto.entity.Account;
import com.marwanmakm.boto.entity.OperationType;
import com.marwanmakm.boto.entity.Tag;
import com.marwanmakm.boto.entity.Transaction;
import com.marwanmakm.boto.service.DataLoaderService;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DataLoaderServiceImpl implements DataLoaderService {

  private final TransactionsDAO transactionsDAO;

  private final AccountsDAO accountsDAO;

  private final TagsDAO tagsDAO;

  @Autowired
  public DataLoaderServiceImpl(
      TransactionsDAO transactionsDAO, AccountsDAO accountsDAO, TagsDAO tagsDAO) {
    this.transactionsDAO = transactionsDAO;
    this.accountsDAO = accountsDAO;
    this.tagsDAO = tagsDAO;
  }

  @Override
  public OperationResponseDto load(MultipartFile file) throws IOException {
    // Map CSV File into Transactions List
    Reader reader = new InputStreamReader(file.getInputStream());
    CSVFormat csvFormat =
        CSVFormat.DEFAULT.builder().setHeader(HEADERS).setSkipHeaderRecord(true).build();

    Iterable<CSVRecord> rows = csvFormat.parse(reader);
    List<Transaction> transactionList = new ArrayList<>();

    Optional<Account> account = Optional.empty();

    for (CSVRecord row : rows) {
      Transaction transaction = new Transaction();
      transaction.setId(row.get("id"));
      transaction.setDate(Date.valueOf(row.get("date")));
      transaction.setAmount(Double.valueOf(row.get("amount")));
      transaction.setComment(row.get("comment"));
      transaction.setOperationType(OperationType.findById(row.get("operation_type")));

      if (!row.get("category_tag").isEmpty()) {
        Optional<Tag> categoryTag = tagsDAO.findById(row.get("category_tag"));
        transaction.setTag(categoryTag.orElse(null));
      }

      if (account.isEmpty()) {
        account = accountsDAO.findById(row.get("account"));
      }
      transaction.setAccount(account.orElseThrow());

      transactionList.add(transaction);
    }

    // Save the transactions in the DB
    transactionsDAO.saveAll(transactionList);

    return new OperationResponseDto(
        new Timestamp(System.currentTimeMillis()),
        "Información guardada",
        "Se ha guardado correctamente la información en la base de datos");
  }
}
