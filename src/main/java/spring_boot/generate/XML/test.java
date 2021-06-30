package spring_boot.generate.XML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import spring_boot.entity.Product;

public class test {
	public static void main(String[] args) {
		String filename = "D:/20202/program/project2/create/test.xlsx";
		List<Product> tempStudentList = new ArrayList<>();
		XSSFWorkbook workbook = null;
		XSSFSheet worksheet = null;
		try {
			workbook = new XSSFWorkbook(filename);
			worksheet = workbook.getSheetAt(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(worksheet.getPhysicalNumberOfRows());
		try {
			for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
				Product tempStudent = new Product();

				XSSFRow row = worksheet.getRow(i);

				tempStudent.setQuantity((int) row.getCell(0).getNumericCellValue());
				tempStudent.setName(String.valueOf(row.getCell(1).getNumericCellValue()));
				tempStudentList.add(tempStudent);
			}
		}catch (Exception e) {
			
		}
		System.out.println("check");
	}
}
