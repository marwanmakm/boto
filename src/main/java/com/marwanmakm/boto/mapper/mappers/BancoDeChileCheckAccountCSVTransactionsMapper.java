package com.marwanmakm.boto.mapper.mappers;

import static com.marwanmakm.boto.entity.OperationType.INCOME;
import static com.marwanmakm.boto.entity.OperationType.OUTCOME;

import com.marwanmakm.boto.mapper.AbstractTransactionsMapper;
import com.marwanmakm.boto.mapper.CSVFileTemplate;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class BancoDeChileCheckAccountCSVTransactionsMapper extends AbstractTransactionsMapper {

  private static final String ACCOUNT_ID = "bdc_check";
  private static final String[] HEADERS = {
    "date", "description", "site", "outcome", "income", "total"
  };

  public BancoDeChileCheckAccountCSVTransactionsMapper() {
    super(ACCOUNT_ID);
  }

  @Override
  public CSVFileTemplate map(InputStream inputStream) throws IOException, ParseException {

    Reader reader = new InputStreamReader(inputStream);
    CSVFormat csvFormat =
        CSVFormat.DEFAULT.builder().setHeader(HEADERS).setSkipHeaderRecord(true).build();

    Iterable<CSVRecord> rows = csvFormat.parse(reader);

    CSVFileTemplate template = new CSVFileTemplate();

    for (CSVRecord row : rows) {
      var rowBuilder = CSVFileTemplate.Row.builder();

      if (!row.get("outcome").isEmpty()) {
        rowBuilder.amount(String.valueOf(Math.abs(Double.parseDouble(row.get("outcome")))));
        rowBuilder.operationType(OUTCOME.getId());
      }

      if (!row.get("income").isEmpty()) {
        rowBuilder.amount(String.valueOf(Math.abs(Double.parseDouble(row.get("income")))));
        rowBuilder.operationType(INCOME.getId());
      }

      rowBuilder.account(ACCOUNT_ID);
      rowBuilder.comment(row.get("description"));

      SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
      Date date = inputFormat.parse(row.get("date"));

      SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
      String format = outputFormat.format(date);
      // TODO: Ajustar formateo de fechas
      rowBuilder.date(format);

      template.addRow(rowBuilder.build());
    }

    return template;
  }
}
