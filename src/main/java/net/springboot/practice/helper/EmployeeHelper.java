package net.springboot.practice.helper;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import net.springboot.practice.model.Employee;
import net.springboot.practice.model.Login;
public class EmployeeHelper {
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

		public static List<Employee> convertExcelToListOfEmployee(InputStream is) {
			List<Employee> list = new ArrayList<>();
//			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
//		    Date date = new Date();  
//		    System.out.println(formatter.format(date));  
			java.util.Date date = new java.util.Date();
//			 LocalDate Date = java.time.LocalDate.now();
//			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
//			   java.util.Date now = LocalDateTime.now();  
			try {
				XSSFWorkbook workbook = new XSSFWorkbook(is);
				
				XSSFSheet sheet = workbook.getSheet("employee");
				
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
					
					Employee l =new Employee();
					
					while(cells.hasNext()) {
						Cell cell = cells.next();
						
						switch (cid) {
						case 0:
							l.setEmployeeId((long) cell.getNumericCellValue());
							break;
						case 1:
							l.setAge((int)cell.getNumericCellValue());
							break;
						case 2:
							l.setBuss_Unit(cell.getStringCellValue());
							break;
						case 3:
							l.setComments(cell.getStringCellValue());
							break;
//						case 4:
//							l.setCreated_Date(date);
//							break;
						case 4:
							l.setDateOfJoining(cell.getDateCellValue());
							break;
						case 5:
							l.setDesignation(cell.getStringCellValue());
							break;
						case 6:
							l.setEmp_name(cell.getStringCellValue());
							break;
						case 7:
							l.setGrade(cell.getStringCellValue());
							break;
						case 8:
							l.setDeputation(cell.getStringCellValue());
							break;
						case 9:
							l.setPro_Assign(cell.getStringCellValue());
							break;
						case 10:
							l.setPro_Name(cell.getStringCellValue());
							break;
						case 11:
							l.setSkill_Set(cell.getStringCellValue());
							break;
						case 12:
							l.setSupervisor_Name(cell.getStringCellValue());
							break;
//						case 14:
//							l.setUpdated_Date(date);
//							break;
						case 13:
							l.setAR(cell.getStringCellValue());
							break;
						case 14:
							l.setTSR(cell.getStringCellValue());
							break;
						case 15:
							l.setLocation(cell.getStringCellValue());
							break;
						default:
							break;
						}
						cid++;
					}
					l.setCreated_Date(date);
					l.setUpdated_Date(date);
					l.setStatus("Active");
					list.add(l);
				}
			
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return list;


		}
}
