package com.company.salaryup;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SalaryUploadExcel {
    final static String BHR_FILE_FIRST_COLUMN_HEADING_REGX = "[b|B]eneficiary\\s?[a|A]ccount";
    public static void main(String[] args) throws IOException {
        String sampleExcel = "Z:\\shared_vm\\corporate\\sample_BAH_SalaryUpload_xlsx.xlsx"; //sample_BAH_SalaryUpload_xlsx.xlsx 090990000003444190614092558.SIF
        //File file = new File(sampleExcel);
        //System.out.println(file.exists());
        //isXLSFile(file);
        readExcelFile(sampleExcel);


        //checkRegex();

    }

    /**
     * Matches following:
     * beneficiaryaccount
     * beneficiary account
     * Beneficiary Account
     * Beneficiary account
     * beneficiary Account
     */
    private static void checkRegex() {
        String[] words = {"beneficiaryaccount",
                            "beneficiary account",
                            "Beneficiary Account",
                            "Beneficiary account",
                            "beneficiary Account"
                        };

        for(String s : words){
            System.out.println(s.matches(BHR_FILE_FIRST_COLUMN_HEADING_REGX));
        }
    }

    private static void readExcelFile(String sampleExcel) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(sampleExcel));

        //Workbook workbook = new XSSFWorkbook(inputStream); // if xlsx -> XSSFWorkbook, if xls -> HSSFWorkbook
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
        }catch (IOException e){
            if(e.getMessage().contains("neither an OLE2 stream, nor an OOXML stream")){
                System.out.println("Not an excel");
            }
        }


        if(workbook != null){
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = firstSheet.iterator();
            int rowCount = 0;
            int headerRowNumber = 0;
            boolean foundHeader = false;

            while (rowIterator.hasNext()){
                rowCount++;
                Row row =  rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                if(isRowEmpty(row)){
                    break;
                }

                int columnNumber = 0;
                while (cellIterator.hasNext()){
                    columnNumber++;
                    Cell cell = cellIterator.next();
                    cell.setCellType(CellType.STRING); //Alays, should read as string. Then the value can be converted if required.

                    String cellValue = cell.getStringCellValue();

                    if(cellValue != null && cellValue.matches(BHR_FILE_FIRST_COLUMN_HEADING_REGX)) { //heading row started
                        headerRowNumber = rowCount;
                        foundHeader = true;
                        break;
                    }

                    /*
                     * Process only after the header row.
                     */
                    if(rowCount > headerRowNumber && foundHeader) {
                        //set record
                        System.out.print(columnNumber + "->" + cellValue + ", ");
                    }
                }
                System.out.println();
            }
        }
    }

    private static boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
        if (row.getLastCellNum() <= 0) {
            return true;
        }
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellTypeEnum() != CellType.BLANK && !"".equals(cell.toString())) {
                return false;
            }
        }
        return true;
    }

    public static boolean isXLSFile(File file){
        try {
            System.out.println(Files.probeContentType(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }






/*    private static void readExcelFile_withJXL(String filePath) throws BiffException, IOException {
        Workbook workbook = Workbook.getWorkbook(new File(filePath));
        String[] sheetNames = workbook.getSheetNames();
        Sheet sheet = workbook.getSheet(sheetNames[0]);
        List<String> fields = new ArrayList<>();

        for(int columns=0; columns < sheet.getColumns(); columns++){
            fields.add(sheet.getCell(columns, 0).getContents());
        }

        for(String f : fields){
            System.out.println(f);
        }
    }*/
}
