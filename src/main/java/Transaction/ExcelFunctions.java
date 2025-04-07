package Transaction;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelFunctions {

    public static FileInputStream input_document;
    public static FileOutputStream output_document;
    private Cell cell = null;
    public static int ScenarioCount =1;
    public ArrayList columnsNames = new ArrayList();
    public static XSSFWorkbook wb;
    private XSSFSheet sheet;
    public static int totalColumns =0;


    public  ExcelFunctions() {
        try {

            wb = new XSSFWorkbook(input_document);
            input_document.close();
            sheet = wb.getSheetAt(0);
            while (true){
                cell = sheet.getRow(0).getCell(totalColumns);
                if (cell == null) {
                    break;
                }
                String names = cell.getStringCellValue();
                columnsNames.add(names.trim());
                totalColumns++;

            }
            while (true){
                Row row = sheet.getRow(ScenarioCount);
                if (row == null) {
                    break;
                }
                ScenarioCount++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public String ReadCell(int iScenario,int Column) throws IOException {

        cell = sheet.getRow(iScenario).getCell(Column);
        DataFormatter formatter = new DataFormatter();
        String value = formatter.formatCellValue(cell);
        return value;
    }

    public void WriteToCell(String results, String runStatus,String comment,int iScenario,int ColumnResults, int ColumnRunStatus, int ColumnComment) throws IOException {

        Row row = sheet.getRow(iScenario);
        Cell cell = row.getCell(ColumnResults);
        if (cell == null) {
            cell = row.createCell(ColumnResults);
        }
        cell.setCellValue(runStatus);

        Cell cell1 = row.getCell(ColumnRunStatus);
        if(cell1 == null)
        {
            cell1 = row.createCell(ColumnRunStatus);
        }
        cell1.setCellValue(results);

        Cell cell2 = row.getCell(ColumnComment);
        if(cell2 == null)
        {
            cell2 = row.createCell(ColumnComment);
        }
        cell2.setCellValue(comment);

        wb.write(output_document);
    }
}

