package com.hp.dit.beetbook.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class FeeCollectionFileExporter {
    public static ByteArrayInputStream contactListToExcelFile(List<Object[]> customers) {
        try(Workbook workbook = new XSSFWorkbook()){
            Sheet sheet = workbook.createSheet("FeeCollectionReport");

            // Create a new font and alter it.
            Font font = workbook.createFont();
            font.setFontHeightInPoints((short)14);
            font.setFontName("Ariel");
            font.setBold(true);

            Font fontCell = workbook.createFont();
            fontCell.setFontHeightInPoints((short)12);
            fontCell.setFontName("Ariel");
            fontCell.setBold(true);


            Row row = sheet.createRow(0);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            headerCellStyle.setBorderBottom(BorderStyle.DOTTED);
            headerCellStyle.setBorderLeft(BorderStyle.DOTTED);
            headerCellStyle.setBorderRight(BorderStyle.DOTTED);
            headerCellStyle.setBorderTop(BorderStyle.DOTTED);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerCellStyle.setFont(font);

            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            // Creating header

            Cell cell = row.createCell(0);
            CellStyle style = workbook.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            style.setBorderBottom(BorderStyle.DOTTED);
            style.setBorderLeft(BorderStyle.DOTTED);
            style.setBorderRight(BorderStyle.DOTTED);
            style.setBorderTop(BorderStyle.DOTTED);


            cell.setCellValue("S.No");
            cell.setCellStyle(headerCellStyle);
            style.setFont(fontCell);


            cell = row.createCell(1);
            cell.setCellValue("Date");
            cell.setCellStyle(headerCellStyle);
            style.setFont(fontCell);

            cell = row.createCell(2);
            cell.setCellValue("Total Amount Collected");
            cell.setCellStyle(headerCellStyle);
            style.setFont(fontCell);

            cell = row.createCell(3);
            cell.setCellValue("Total Amount Deposited");
            cell.setCellStyle(headerCellStyle);
            style.setFont(fontCell);

            cell = row.createCell(4);
            cell.setCellValue("Balance");
            cell.setCellStyle(headerCellStyle);
            style.setFont(fontCell);





            // Creating data rows for each customer
            for(int i = 0; i < customers.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(i+1);
                dataRow.createCell(1).setCellValue((String) customers.get(i)[4]);
                dataRow.createCell(2).setCellValue(String.valueOf((BigDecimal) customers.get(i)[0]));
                dataRow.createCell(3).setCellValue(String.valueOf((BigDecimal) customers.get(i)[1]));
                dataRow.createCell(4).setCellValue(String.valueOf((BigDecimal) customers.get(i)[2]));
            }

            // Making size of column auto resize to fit with data
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);


            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }


//
}
