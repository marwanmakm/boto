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
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

// FIXME: Actualmente, este mapper no funciona. Ocurren problemas de parseo con la data en excel
public class BancoDeChileCheckAccountTransactionsMapper extends AbstractTransactionsMapper {

  private static final String ACCOUNT_ID = "bdc_check";
  private static final Integer INITIAL_ROW = 27;
  private static final Integer INITIAL_COLUMN = 1;

  private static final Integer DATE_COLUMN = 1;
  private static final Integer DESCRIPTION_COLUMN = 2;
  private static final Integer OUTCOME_COLUMN = 4;
  private static final Integer INCOME_COLUMN = 5;

  private static final Integer BREAK_LIMIT = 200;

  public BancoDeChileCheckAccountTransactionsMapper() {
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

      if (r.getCell(OUTCOME_COLUMN).getCellType() != CellType.BLANK) {
        rowBuilder.amount(
            String.valueOf(Math.abs(r.getCell(OUTCOME_COLUMN).getNumericCellValue())));
        rowBuilder.operationType(OUTCOME.getId());
      }

      if (r.getCell(INCOME_COLUMN).getCellType() != CellType.BLANK) {
        rowBuilder.amount(String.valueOf(Math.abs(r.getCell(INCOME_COLUMN).getNumericCellValue())));
        rowBuilder.operationType(INCOME.getId());
      }

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
