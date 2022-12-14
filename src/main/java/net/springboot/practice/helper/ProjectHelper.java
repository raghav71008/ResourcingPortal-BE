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


import net.springboot.practice.model.Project;

public class ProjectHelper {
	public static boolean checkExcelFormat(MultipartFile file) {
		String contentType = file.getContentType();

		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	public static List<Project> convertExcelToListOfProject(InputStream is) {
		List<Project> list = new ArrayList<>();
		java.util.Date date = new java.util.Date();
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			
			XSSFSheet sheet = workbook.getSheet("project");
			
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
				
				Project l =new Project();
				
				while(cells.hasNext()) {
					Cell cell = cells.next();
					
					switch (cid) {
					case 0:
						l.setAcc_managers(cell.getStringCellValue());
						break;
					case 1:
						l.setBillable(cell.getStringCellValue());					
						break;
					case 2:
						l.setBuss_unit(cell.getStringCellValue());						
						break;
					case 3:
						l.setDepartment(cell.getStringCellValue());					
						break;
					case 4:
						l.setEnd_date(cell.getDateCellValue());						
						break;
					case 5:
						l.setFin_Id((long) cell.getNumericCellValue());
						break;
					case 6:
						l.setPmo_submitter(cell.getStringCellValue());						
						break;
					case 7:
						l.setProj_Id(cell.getStringCellValue());
						break;
					case 8:
						l.setProj_name(cell.getStringCellValue());
						break;
					case 9:
						l.setStart_date(cell.getDateCellValue());
						break;
					case 10:
						l.setVertical(cell.getStringCellValue());
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
