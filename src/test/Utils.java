package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class Utils {


  /**
   * Read a table from an excel file
   * 
   * @param xlFilePath - file location
   * @param sheetNum - sheet number
   * @return an array of arrays, containing all data read from the table
   */
  public static String[][] readXLSXFile(String xlFilePath, int sheetNum) {
    String[][] tabArray = null;

    try {
      FileInputStream file = new FileInputStream(new File(xlFilePath));

      // Get the workbook instance for XLS file
      HSSFWorkbook workbook = new HSSFWorkbook(file);

      // Get selected sheet from the workbook
      Sheet sheet = workbook.getSheetAt(0);

      // calculate sheet's size
      int colNr = sheet.getRow(0).getLastCellNum();
      int rowNr = sheet.getLastRowNum();
      tabArray = new String[rowNr][colNr];

      // Iterate through each row from selected sheet

      // Solution 1
      for (int i = 0; i < rowNr; i++) {
        Row row = sheet.getRow(i + 1);// skip first row because it contains the headers
        for (int j = 0; j < colNr; j++) {
          Cell cell = row.getCell(j); // To read value from each col in each row
          tabArray[i][j] = cellToString(cell);
          System.out.println(tabArray[i][j]);
        }
      }

      file.close();
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return tabArray;
  }

  public static String cellToString(Cell cell) {
    // This function will convert an object of type excel cell to a string value
    int type = cell.getCellType();
    Object result;
    switch (type) {
    case Cell.CELL_TYPE_NUMERIC: // 0
    	
    	double num = (int) cell.getNumericCellValue();
    	DecimalFormat df = new DecimalFormat("#");
        result = df.format(num);
      break;
    case Cell.CELL_TYPE_STRING: // 1
      result = cell.getStringCellValue();
      break;
    case Cell.CELL_TYPE_FORMULA: // 2
      throw new RuntimeException("We can't evaluate formulas in Java");
    case Cell.CELL_TYPE_BLANK: // 3
      result = "-";
      break;
    case Cell.CELL_TYPE_BOOLEAN: // 4
      result = cell.getBooleanCellValue();
      break;
    case Cell.CELL_TYPE_ERROR: // 5
      throw new RuntimeException("This cell has an error");
    default:
      throw new RuntimeException("We don't support this cell type: " + type);
    }
    return result.toString();
  }

}
