package com.marwanmakm.boto.mapper.mappers;

import static com.marwanmakm.boto.entity.OperationType.INCOME;
import static com.marwanmakm.boto.entity.OperationType.OUTCOME;

import com.marwanmakm.boto.mapper.AbstractTransactionsMapper;
import com.marwanmakm.boto.mapper.CSVFileTemplate;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class BancoDeChileCreditCardTransactionsMapper extends AbstractTransactionsMapper {

  private static final String ACCOUNT_ID = "bdc_tdc";
  private static final Integer INITIAL_ROW = 18;
  private static final Integer INITIAL_COLUMN = 1;

  private static final Integer DATE_COLUMN = 1;
  private static final Integer DESCRIPTION_COLUMN = 4;
  private static final Integer AMOUNT_COLUMN = 10;

  private static final Integer BREAK_LIMIT = 200;

  public BancoDeChileCreditCardTransactionsMapper() {
    super(ACCOUNT_ID);
  }

  @Override
  public CSVFileTemplate map(InputStream inputStream) throws IOException, ParseException {
    Workbook excelFile = WorkbookFactory.create(inputStream);
    Sheet sheet = excelFile.getSheetAt(0);

    Row r = sheet.getRow(INITIAL_ROW);

    if (r.getCell(INITIAL_COLUMN) == null) {
      throw new IllegalArgumentException("Archivo vacío");
    }

    CSVFileTemplate template = new CSVFileTemplate();

    for (int i = INITIAL_ROW; i < BREAK_LIMIT; i++) {
      var rowBuilder = CSVFileTemplate.Row.builder();

      // TODO: Ajustar convención de ingreso y egreso de esta cuenta
      var amount = r.getCell(AMOUNT_COLUMN).getNumericCellValue();
      rowBuilder.operationType(amount < 0 ? INCOME.getId() : OUTCOME.getId());
      rowBuilder.amount(String.valueOf(Math.abs(amount)));
      rowBuilder.account(ACCOUNT_ID);
      rowBuilder.comment(r.getCell(DESCRIPTION_COLUMN).getStringCellValue());

      String dateValue = r.getCell(DATE_COLUMN).getStringCellValue();
      SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
      Date date = inputFormat.parse(dateValue);

      SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
      String format = outputFormat.format(date);
      // TODO: Ajustar formateo de fechas
      rowBuilder.date(format);

      template.addRow(rowBuilder.build());

      r = sheet.getRow(i + 1);

      // Check if the next cell is null or empty to break the loop
      if (r.getCell(DATE_COLUMN) == null || r.getCell(DATE_COLUMN).getStringCellValue().isEmpty()) {
        break;
      }
    }

    return template;
  }
}
