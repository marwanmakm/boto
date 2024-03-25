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

  // time-milis_source_transactions.csv
  private static final String FILE_PATH_FORMAT = "./export/%s_%s_transactions.csv";
  public static final String[] HEADERS = {
    "composed_id", "date", "time", "amount", "comment", "account", "operation_type", "category_tag"
  };

  private List<Row> rows;

  private static String createNewFilePath(String sourceType) {
    String currentMilis = String.valueOf(System.currentTimeMillis());
    return String.format(FILE_PATH_FORMAT, currentMilis, sourceType);
  }

  public void addRow(Row row) {
    if (this.rows == null) {
      this.rows = new ArrayList<>();
    }
    this.rows.add(row);
  }

  public void createFile(String sourceType) throws IOException {
    // Create FileWriter object with file path
    FileWriter fileWriter = new FileWriter(createNewFilePath(sourceType));

    // Create CSVPrinter object with custom CSVFormat
    CSVPrinter csvPrinter =
        new CSVPrinter(fileWriter, CSVFormat.DEFAULT.builder().setHeader(HEADERS).build());

    // Write records to the CSV file
    for (Row row : rows) {
      csvPrinter.printRecord(row.toValueArray());
    }
    csvPrinter.flush();
    csvPrinter.close();

    System.out.println("CSV file created successfully.");
  }

  @Builder
  public record Row(
      String date,
      @Nullable String time,
      String amount,
      @Nullable String comment,
      String account,
      String operationType,
      @Nullable String categoryTag) {
    private static String checkEmpty(String value) {
      return value != null ? value : "";
    }

    public List<String> toValueArray() {

      // TODO: Acomodar el valor por defecto del tiempo
      final var composedId = String.format("%s_%s_%s", date, checkEmpty(time), account);
      return List.of(
          composedId,
          date,
          checkEmpty(time),
          amount,
          checkEmpty(comment),
          account,
          operationType,
          checkEmpty(categoryTag));
    }
  }
}
