package sample.service;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ExcelService {


    public void fillTable(Map<String, Pattern> map, String pathOut) {
        try (XSSFWorkbook wb = new XSSFWorkbook()) {
            for(Map.Entry<String, Pattern> entry : map.entrySet()) {
                //Здесь будет метод возвращающий листы
                BoxCreate boxCreate = new BoxCreate();
                BoxOfVariablesList boxOfVariablesList = boxCreate.fillBox(entry.getKey());
                List<Double> time = boxOfVariablesList.getTime();
                List<Double> variable = boxOfVariablesList.getVariable();
                AreaReference reference = wb.getCreationHelper().createAreaReference(
                        new CellReference(0, 0), new CellReference(time.size() + 1, 1));
                 XSSFSheet sheet = wb.createSheet(entry.getKey());
                 XSSFTable table = sheet.createTable(reference);

                 table.getCTTable().addNewTableStyleInfo();
                 table.getCTTable().getTableStyleInfo().setName("TableStyleMedium2");

                 XSSFRow titleRow = sheet.createRow(0);
                 XSSFCell cellTime = titleRow.createCell(0);
                 XSSFCell cellVariable = titleRow.createCell(1);

                 cellTime.setCellValue("Time");
                 cellVariable.setCellValue(entry.getKey());

                XSSFRow row;
                XSSFCell cell0;
                XSSFCell cell1;
                for (int i = 0; i < time.size(); i++) {
                    row = sheet.createRow(i + 1);
                    cell0 = row.createCell(0);
                    cell1 = row.createCell(1);
                    cell0.setCellValue(time.get(i));
                    cell1.setCellValue(variable.get(i));
                }

             }
            try (FileOutputStream fileOut = new FileOutputStream(pathOut + "/Data.xlsx")) {
                wb.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    }
