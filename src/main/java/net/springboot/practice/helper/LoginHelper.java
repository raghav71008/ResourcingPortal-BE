package net.springboot.practice.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import net.springboot.practice.model.Login;

public class LoginHelper {
	// check file type
	public static boolean checkExcelFormat(MultipartFile file) {
		String contentType = file.getContentType();

		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}
	}

	// excel to list

	public static List<Login> convertExcelToListOfLogin(InputStream is) {
		List<Login> list = new ArrayList<>();
		 
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			
			XSSFSheet sheet = workbook.getSheet("login");
			
			int rowNumber = 0;
			Iterator<Row> iterator = sheet.iterator();
			
			while(iterator.hasNext()) {
				Row row = iterator.next();
				
				if(rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cells = row.iterator();
				
				int cid = 0;
				
				Login l =new Login();
				
				while(cells.hasNext()) {
					Cell cell = cells.next();
					
					switch (cid) {
					case 0:
						l.setEmpId((long) cell.getNumericCellValue());
						break;
					case 1:
						l.setPassword(cell.getStringCellValue());
						break;
					case 2:
						l.setRole( cell.getStringCellValue());
						break;
					case 3:
						l.setStatus(cell.getStringCellValue());
						break;
					default:
						break;
					}
					cid++;
				}
				list.add(l);
			}
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;


	}
}
