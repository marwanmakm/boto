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

public class SantanderCheckAccountCSVTransactionsMapper extends AbstractTransactionsMapper {

  private static final double ZERO = 0.0;
  private static final String ACCOUNT_ID = "santander_check";
  private static final String[] HEADERS = {
    "id", "date", "sucursal", "description", "document", "outcome", "income", "category_tag"
  };

  public SantanderCheckAccountCSVTransactionsMapper() {
    super(ACCOUNT_ID);
  }

  @Override
  public CSVFileTemplate map(InputStream inputStream) throws IOException, ParseException {

    Reader reader = new InputStreamReader(inputStream);
    CSVFormat csvFormat =
        CSVFormat.DEFAULT.builder().setHeader(HEADERS).setSkipHeaderRecord(true).build();

    Iterable<CSVRecord> rows = csvFormat.parse(reader);

    CSVFileTemplate template = new CSVFileTemplate();

    double outcome = ZERO;
    double income = ZERO;

    for (CSVRecord row : rows) {
      var rowBuilder = CSVFileTemplate.Row.builder();

      if (!row.get("outcome").isEmpty()) {
        outcome = Double.parseDouble(row.get("outcome"));
        if (outcome != ZERO) {
          rowBuilder.amount(String.valueOf(Math.abs(outcome)));
          rowBuilder.operationType(OUTCOME.getId());
        }
      }

      if (!row.get("income").isEmpty()) {
        income = Double.parseDouble(row.get("income"));
        if (income != ZERO) {
          rowBuilder.amount(String.valueOf(Math.abs(income)));
          rowBuilder.operationType(INCOME.getId());
        }
      }

      // This instruction is given by the fact that in the report, some rows have zero and zero
      if (income == ZERO && outcome == ZERO) {
        continue;
      }

      rowBuilder.id(row.get("id"));
      rowBuilder.account(ACCOUNT_ID);
      rowBuilder.comment(row.get("description"));
      rowBuilder.categoryTag(row.get("category_tag"));

      SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
      Date date = inputFormat.parse(row.get("date"));

      SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
      String format = outputFormat.format(date);
      rowBuilder.date(format);

      template.addRow(rowBuilder.build());
    }

    return template;
  }
}
