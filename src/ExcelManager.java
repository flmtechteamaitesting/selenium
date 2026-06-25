import org.apache.poi.xssf.usermodel.*;
import java.io.*;

public class ExcelManager {
    public static void main(String[] args) {
        String filePath = "TestData1.xlsx";

        // 1. WRITING DATA
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("LoginData");
            XSSFSheet sheet1 = workbook.createSheet("LoginData1");

            // Create a row and cells
            sheet.createRow(0).createCell(0).setCellValue("Username");
            sheet.getRow(0).createCell(1).setCellValue("Password");
            sheet.createRow(1).createCell(0).setCellValue("Kishore");
            sheet.getRow(1).createCell(1).setCellValue("Secret123");

            // Write the output to a file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
                System.out.println("File written successfully!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. READING DATA
//        try (FileInputStream fis = new FileInputStream(filePath);
//             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
//            
//            XSSFSheet sheet = workbook.getSheet("LoginData");
//            
//            // Get the value from the second row, first column
//            String username = sheet.getRow(1).getCell(0).getStringCellValue();
//            String password = sheet.getRow(1).getCell(1).getStringCellValue();
//            
//            System.out.println("Read from Excel -> Username: " + username + ", Password: " + password);
//            
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}