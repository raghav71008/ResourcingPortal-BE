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

import net.springboot.practice.model.Allocations;
import net.springboot.practice.model.Employee;

public class AllocationsHelper {
	public static boolean checkExcelFormat(MultipartFile file) {
		String contentType = file.getContentType();

		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}
	}
	
	// excel to list

	public static List<Allocations> convertExcelToListOfAllocations(InputStream is) throws java.lang.IllegalStateException{
		List<Allocations> list = new ArrayList<>();
		java.util.Date date = new java.util.Date();
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			
			XSSFSheet sheet = workbook.getSheet("allocation");
			
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
				Employee emp = new Employee();
				Allocations l =new Allocations();
				
				while(cells.hasNext()) {
					Cell cell = cells.next();
					
					switch (cid) {
					case 0:
						l.setAllocate_EndDate(cell.getDateCellValue());
						break;
					case 1:
						l.setAllocate_Percentage((int) cell.getNumericCellValue());
						break;
					case 2:
						l.setAllocate_StartDate(cell.getDateCellValue());
						break;
//					case 3:
//						l.setCreated_Date(cell.getDateCellValue());
//						//System.out.println(cell.getDateCellValue());
//						break;
					case 3:
						l.setEmployeeId((long) cell.getNumericCellValue());
						emp.setEmployeeId((long) cell.getNumericCellValue());
						l.setEmployee(emp);
						//System.out.println(cell.getNumericCellValue());
						l.setEmpp_id((long) cell.getNumericCellValue());
						
//						l.setEmpp_id((long) cell.getNumericCellValue());
						break;
					case 4:	
						l.setPro_Id(cell.getStringCellValue());
						break;
					case 5:
						l.setPro_Name(cell.getStringCellValue());
						break;
//					case 7:
//						l.setUpdated_Date(cell.getDateCellValue());
//						break;
					case 6:
						l.setFin_id((int) cell.getNumericCellValue());
						break;
					case 7:
						l.setEmp_Name(cell.getStringCellValue());
						break;
 
					default:
						break;
					}
					
					cid++;
				}
				l.setCreated_Date(date);
				l.setUpdated_Date(date);
				list.add(l);
			}
		
		}
//		catch(java.lang.IllegalStateException ex){
////			System.out.print("hell");
//		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;


	}
}
