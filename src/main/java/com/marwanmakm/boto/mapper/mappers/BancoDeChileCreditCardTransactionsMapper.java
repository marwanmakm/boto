package com.marwanmakm.boto.mapper.mappers;

import com.marwanmakm.boto.mapper.AbstractTransactionsMapper;
import com.marwanmakm.boto.mapper.CSVFileTemplate;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class BancoDeChileCreditCardTransactionsMapper extends AbstractTransactionsMapper {

  private static final Integer INITIAL_ROW = 18;
  private static final Integer INITIAL_COLUMN = 1;

  private static final Integer DATE_COLUMN = 1;
  private static final Integer DESCRIPTION_COLUMN = 4;
  private static final Integer AMOUNT_COLUMN = 10;

  private static final Integer BREAK_LIMIT = 200;

  public BancoDeChileCreditCardTransactionsMapper() {
    super("bdc_tdc");
  }

  @Override
  public CSVFileTemplate map(InputStream inputStream) throws IOException {
    Workbook excelFile = new HSSFWorkbook(inputStream);
    Sheet sheet = excelFile.getSheetAt(0);

    Row r = sheet.getRow(INITIAL_ROW);

    if (r.getCell(INITIAL_COLUMN) == null) {
      throw new IllegalArgumentException("Archivo vacío");
    }

    CSVFileTemplate template = new CSVFileTemplate();

    for (int i = INITIAL_ROW; i < BREAK_LIMIT; i++) {
      var rowBuilder = CSVFileTemplate.Row.builder();

      rowBuilder.date(r.getCell(DATE_COLUMN).getStringCellValue());
      rowBuilder.comment(r.getCell(DESCRIPTION_COLUMN).getStringCellValue());
      rowBuilder.amount(String.valueOf(r.getCell(AMOUNT_COLUMN).getNumericCellValue()));

      template.addRow(rowBuilder.build());

      r = sheet.getRow(i + 1);

      // Check if the next cell is null to break the loop
      if (r.getCell(DATE_COLUMN) == null) {
        break;
      }
    }

    return template;
  }
}
