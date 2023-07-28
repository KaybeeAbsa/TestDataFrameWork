package Add_OD_Amount;

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


    public ExcelFunctions() {
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

    public void WriteToCell(String results, String runStatus, String cardNO, int iScenario,int ColumnCount, int ColumnC, int ColumncardNo) throws IOException {

        Row row = sheet.getRow(iScenario);
        Cell cell = row.getCell(ColumnCount);
        if (cell == null) {
            cell = row.createCell(ColumnCount);
        }
        cell.setCellValue(runStatus);

        Cell cell1 = row.getCell(ColumnC);
        if(cell1 == null)
        {
            cell1 = row.createCell(ColumnC);
        }
        cell1.setCellValue(results);

        Cell cell2 = row.getCell(ColumncardNo);
        if(cell2 == null)
        {
            cell2 = row.createCell(ColumncardNo);
        }
        cell2.setCellValue(cardNO);

        wb.write(output_document);
    }
}

