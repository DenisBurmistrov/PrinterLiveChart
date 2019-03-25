package sample.service.excel;

import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.charts.XSSFChartLegend;
import sample.entity.BoxOfVariablesList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ExcelService {


    public void fillTable(Map<String, Pattern> map, String pathOut, String pathRead) {
        try (XSSFWorkbook wb = new XSSFWorkbook()) {
            for (Map.Entry<String, Pattern> entry : map.entrySet()) {
                //Здесь будет метод возвращающий листы
                BoxCreateService boxCreateService = new BoxCreateService();
                BoxOfVariablesList boxOfVariablesList = boxCreateService.fillBox(entry.getKey(), pathRead);
                List<Long> time = boxOfVariablesList.getTime();
                List<Double> variable = boxOfVariablesList.getVariable();
                AreaReference reference = wb.getCreationHelper().createAreaReference(
                        new CellReference(0, 0), new CellReference(time.size() + 1, 1));
                XSSFSheet sheet = wb.createSheet(entry.getKey());
                XSSFTable table = sheet.createTable(reference);

                table.setName("Test");
                table.setDisplayName("Test_Table");

                table.getCTTable().addNewTableStyleInfo();
                table.getCTTable().getTableStyleInfo().setName("TableStyleMedium2");

                XSSFRow titleRow = sheet.createRow(0);
                XSSFCell cellTime = titleRow.createCell(0);
                XSSFCell cellVariable = titleRow.createCell(1);

                cellTime.setCellValue("Время");
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

                /* At the end of this step, we have a worksheet with test data, that we want to write into a chart */
                /* Create a drawing canvas on the worksheet */
                XSSFDrawing xlsx_drawing = sheet.createDrawingPatriarch();
                /* Define anchor points in the worksheet to position the chart */
                XSSFClientAnchor anchor = xlsx_drawing.createAnchor(10, 10, 10, 10, 3, 0, 28, 38);
                /* Create the chart object based on the anchor point */
                XSSFChart my_line_chart = xlsx_drawing.createChart(anchor);
                /* Define legends for the line chart and set the position of the legend */
                XSSFChartLegend legend = my_line_chart.getOrCreateLegend();
                legend.setPosition(LegendPosition.BOTTOM);
                /* Create data for the chart */
                LineChartData data = my_line_chart.getChartDataFactory().createLineChartData();
                /* Define chart AXIS */
                ChartAxis bottomAxis = my_line_chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
                ValueAxis leftAxis = my_line_chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
                leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
                /* Define Data sources for the chart */
                /* Set the right cell range that contain values for the chart */
                /* Pass the worksheet and cell range address as inputs */
                /* Cell Range Address is defined as First row, last row, first column, last column */
                ChartDataSource<Number> xs = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(1, time.size(), 0, 0));
                ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(1,variable.size(), 1, 1));
                /* Add chart data sources as data to the chart */
                data.addSeries(xs, ys1);
                /* Plot the chart with the inputs from data and chart axis */
                my_line_chart.plot(data, bottomAxis, leftAxis);
                /* Finally define FileOutputStream and write chart information */

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
