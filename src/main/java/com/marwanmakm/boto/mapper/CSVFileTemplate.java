package com.marwanmakm.boto.mapper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.lang.Nullable;

public class CSVFileTemplate {

  // timemilis_source_transactions.csv
  private static final String FILE_PATH_FORMAT = "./export/%s_%s_transactions";

  private List<Row> rows;

  public void addRow(Row row) {
    if (this.rows == null) {
      this.rows = new ArrayList<>();
    }
    this.rows.add(row);
  }

  public void createFile() throws IOException {
    // Create FileWriter object with file path
    FileWriter fileWriter = new FileWriter(createNewFilePath());

    // Create CSVPrinter object with custom CSVFormat
    CSVPrinter csvPrinter =
        new CSVPrinter(
            fileWriter,
            CSVFormat.DEFAULT.withHeader(
                "composed_id",
                "date",
                "time",
                "amount",
                "comment",
                "account",
                "operation_type",
                "category_tag"));

    // Write records to the CSV file
    for (Row row : rows) {
      csvPrinter.printRecord(row.toValueArray());
    }
    csvPrinter.flush();
    csvPrinter.close();

    System.out.println("CSV file created successfully.");
  }

  // TODO: Repair File Path
  private static String createNewFilePath() {
    String currentMilis = String.valueOf(System.currentTimeMillis());
    String bankId = "bdc_tdc";

    return String.format(FILE_PATH_FORMAT, currentMilis, bankId);
  }

  @Builder
  public record Row(
      String composedId,
      String date,
      @Nullable String time,
      String amount,
      @Nullable String comment,
      String account,
      String operationType,
      @Nullable String categoryTag) {
    public List<String> toValueArray() {

      return List.of(composedId, date, time, amount, comment, account, operationType, categoryTag);
    }
  }
}
