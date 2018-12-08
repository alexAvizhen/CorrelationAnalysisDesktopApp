package com.bsuir.lagunovskaya.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;

public class ExcelWriterUtils {
    private static String SASHA_PREFIX_PATH = "C:\\Projects\\LagunovskayaCourseProject\\src\\main\\resources\\";
    private static String SASHA_TEMPLATE_EXCEL_FILE = "C:\\Projects\\LagunovskayaCourseProject\\src\\main\\resources\\template.xlsx";
    private static String SASHA_TEMP_TEMPLATE_EXCEL_FILE = "C:\\Projects\\LagunovskayaCourseProject\\src\\main\\resources\\temp_template.xlsx";

    private static String LIZA_PREFIX_PATH = "E:\\УНИВЕР\\JavaProjects\\CorrelationAnalysisDesktopApp\\src\\main\\resources\\";
    private static String LIZA_TEMPLATE_EXCEL_FILE = "E:\\УНИВЕР\\JavaProjects\\CorrelationAnalysisDesktopApp\\src\\main\\resources\\template.xlsx";
    private static String LIZA_TEMP_TEMPLATE_EXCEL_FILE = "E:\\УНИВЕР\\JavaProjects\\CorrelationAnalysisDesktopApp\\src\\main\\resources\\temp_template.xlsx";

    //необходимо подменить под свой компьютер
    private static String PREFIX_PATH = LIZA_PREFIX_PATH;
    private static String TEMPLATE_EXCEL_FILE = LIZA_TEMPLATE_EXCEL_FILE;
    private static String TEMP_TEMPLATE_EXCEL_FILE = LIZA_TEMP_TEMPLATE_EXCEL_FILE;

    public static String writeToExcelFile(String writeFileName, List<Double> firstArray, List<Double> secondArray) {

        String resultExcelFilePath = PREFIX_PATH + writeFileName;
        try {
            // Obtain a workbook from the excel file
            //файл с шаблоном
            File templateFile = new File(TEMPLATE_EXCEL_FILE);
            File tempTemplateFile = new File(TEMP_TEMPLATE_EXCEL_FILE);
            //копируем темплейт файл во временный темплейт, потому что есть ошибка в apache poi, что после работы с эксель файлом из джавы изменяется и темплейт файл и результируюущий файл
            copyFileUsingChannel(templateFile, tempTemplateFile);
            Workbook workbook = WorkbookFactory.create(tempTemplateFile);
            // чтобы после заполнения формулы пересчитались
            workbook.setForceFormulaRecalculation(true);
            // Get Sheet at index 0
            Sheet sheet = workbook.getSheetAt(0);
            //размер первого массива должен быть равен второму
            //заполняем колонки A(первым массивом) и B(вторым массивом)
            int columnANumb = 0;
            int columnBNumb = 1;
            for (int i = 0; i < firstArray.size(); i++) {
                Row currentRow = getRowOrCreate(sheet, i);
                //заполняем A колонку строки rowNumb i-ым элементом из первого массива
                Cell cellAColumn = getCellOrCreate(currentRow, columnANumb);
                cellAColumn.setCellValue(firstArray.get(i));
                //заполняем B колонку строки rowNumb i-ым элементом из второго массива
                Cell cellBColumn = getCellOrCreate(currentRow, columnBNumb);
                cellBColumn.setCellValue(secondArray.get(i));
            }
            // когда заполнили эксель файл в памяти, необходимо записать его в базу
            FileOutputStream fileOut = new FileOutputStream(resultExcelFilePath);
            workbook.write(fileOut);
            // Closing the workbook
            //сливем изменения в файлы
            workbook.close();
            fileOut.close();

            //удаляем временный темплейт файл, так как он равен результрующему и используется только как шаблог
            tempTemplateFile.delete();




        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultExcelFilePath;
    }

    private static Row getRowOrCreate(Sheet sheet, int rowNumb) {
        Row resultRow = sheet.getRow(rowNumb);
        if (resultRow == null) {
            resultRow = sheet.createRow(rowNumb);
        }
        return resultRow;
    }

    private static Cell getCellOrCreate(Row row, int columnNumb) {
        Cell resultCell = row.getCell(columnNumb);
        //Create the cell if it doesn't exist
        if (resultCell == null) {
            resultCell = row.createCell(columnNumb);
        }
        return resultCell;
    }

    private static void copyFileUsingChannel(File source, File destination) throws IOException {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(destination).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        }finally{
            sourceChannel.close();
            destChannel.close();
        }
    }
}
