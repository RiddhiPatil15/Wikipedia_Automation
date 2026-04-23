package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    public static List<String> getAllData() {

        List<String> dataList = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("testdata/data.xlsx");
            Workbook wb = new XSSFWorkbook(fis);
            Sheet sheet = wb.getSheetAt(0);

            int rowCount = sheet.getLastRowNum();

            for (int i = 1; i <= rowCount; i++) {

                Row row = sheet.getRow(i);

                if (row == null || row.getCell(0) == null) {
                    continue;
                }

                Cell cell = row.getCell(0);

                cell.setCellType(CellType.STRING);

                String value = cell.getStringCellValue().trim();

                if (!value.isEmpty()) {
                    dataList.add(value);
                }
            }

            wb.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }
}