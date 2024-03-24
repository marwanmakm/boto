package com.marwanmakm.boto.mapper;

import org.springframework.stereotype.Component;

@Component
public class CSVEntityMapper {
  //
  //  public List<Transaction> mapCSVtoTransactionEntity(MultipartFile file) throws IOException {
  //
  //    Reader reader = new FileReader(file.getName());
  //    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
  //        .setHeader("composed_id",
  //            "date",
  //            "time",
  //            "amount",
  //            "comment",
  //            "account",
  //            "operation_type",
  //            "category_tag")
  //        .setSkipHeaderRecord(true)
  //        .build();
  //
  //    Iterable<CSVRecord> rows = csvFormat.parse(reader);
  //    List<Transaction> transactionList = new ArrayList<>();
  //
  //    for (CSVRecord row : rows){
  //      Transaction transaction = new Transaction();
  //
  //      transaction.setComposedId(row.get("composed_id"));
  //      transaction.setDate(Date.valueOf(row.get("composed_id")));
  //      transaction.setTime(Time.valueOf(row.get("composed_id")));
  //      transaction.setAmount(Double.valueOf(row.get("composed_id")));
  //      transaction.setComment(row.get("composed_id"));
  //      transaction.setAccount(null);
  //      transaction.setOperationType(null);
  //      transaction.setCategoryTag(null);
  //
  //      transactionList.add(transaction);
  //    }
  //
  //    return transactionList;
  // }
}
