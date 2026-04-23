package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    public static List<String> getKeywords(String filePath) {

        List<String> keywords = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {

                Cell cell = row.getCell(0);

                if (cell != null) {
                    String value = cell.toString().trim();

                    if (!value.isEmpty()) {
                        keywords.add(value);
                    }
                }
            }

            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return keywords;
    }
}
