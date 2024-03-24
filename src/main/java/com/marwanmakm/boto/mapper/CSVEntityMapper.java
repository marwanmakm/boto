package com.marwanmakm.boto.mapper;

import com.marwanmakm.boto.entity.Transaction;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CSVEntityMapper {

  public List<Transaction> mapCSVtoTransactionEntity(MultipartFile file) throws IOException {

    Reader reader = new FileReader(file.getName());
    CSVFormat csvFormat =
        CSVFormat.DEFAULT
            .builder()
            .setHeader(
                "composed_id",
                "date",
                "time",
                "amount",
                "comment",
                "account",
                "operation_type",
                "category_tag")
            .setSkipHeaderRecord(true)
            .build();

    Iterable<CSVRecord> rows = csvFormat.parse(reader);
    List<Transaction> transactionList = new ArrayList<>();

    for (CSVRecord row : rows) {
      Transaction transaction = new Transaction();

      transaction.setComposedId(row.get("composed_id"));
      transaction.setDate(Date.valueOf(row.get("composed_id")));
      transaction.setTime(Time.valueOf(row.get("composed_id")));
      transaction.setAmount(Double.valueOf(row.get("composed_id")));
      transaction.setComment(row.get("composed_id"));
      transaction.setAccount(null);
      transaction.setOperationType(null);
      transaction.setCategoryTag(null);

      transactionList.add(transaction);
    }

    return transactionList;
  }
}
