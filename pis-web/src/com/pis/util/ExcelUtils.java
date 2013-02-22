package com.pis.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {	
	
	public static List<Map<String, Object>> readExcel(String filePath) throws Exception{
		return  readExcel(filePath, null);
	}
	
	/**
	 * 
	 * @param filePath
	 * @param columnKeyMap
	 * @return
	 */
	public static List<Map<String, Object>> readExcel(String filePath, Map<String, String> columnKeyMap) throws Exception{
		File file = new File(filePath);
		if(!file.exists())
			throw new Exception("There is an unknow exception.");
		
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filePath));	
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		int rowCount = sheet.getLastRowNum();
		HSSFRow rowNames =  sheet.getRow(0);
		
		if(columnKeyMap == null || columnKeyMap.keySet().size() == 0){
			columnKeyMap = new HashMap<String, String>();
			for(int col = 0; ; col++){
				if(rowNames.getCell(col) == null) break;
				String cellValue =  rowNames.getCell(col).getStringCellValue();
				columnKeyMap.put(cellValue, cellValue);
			}
		}			
		
		for(int i = 1; i < rowCount + 1; i++ ){
			Map<String, Object> item = new HashMap<String, Object>();
			HSSFRow row = sheet.getRow(i);
			for(int colIndex = 0; colIndex < row.getLastCellNum(); colIndex++){
				HSSFCell cell = row.getCell(colIndex);				
				if(cell == null) break;

				Object value;
				DecimalFormat df = new DecimalFormat("#.##########");
				switch (cell.getCellType()){ 
	                case HSSFCell.CELL_TYPE_NUMERIC: // 数字   
	                	if (HSSFDateUtil.isCellDateFormatted(cell)) {   
	                        //如果是date类型则 ，获取该cell的date值   
	                		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                        value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
	                    } else { 
	                    	//纯数字 ，防止如1.323232E8 类型的数据
	                    	String val = String.valueOf(df.format(cell.getNumericCellValue()));
	                    	int pos = val.indexOf(".");
	                    	//如果数值带有小数
	                        value = Double.parseDouble(val.substring(pos + 1)) > 0 ? val: val.substring(0, pos - 1);	                        
	                    }
	                    break;   
	                case HSSFCell.CELL_TYPE_STRING: // 字符串   
	                	value = cell.getStringCellValue();
	                    break;   
	                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean   
	                	value = cell.getBooleanCellValue();
	                    break;   
	                case HSSFCell.CELL_TYPE_FORMULA: // 公式   
	                	value = cell.getCellFormula();
	                    break;   
	                case HSSFCell.CELL_TYPE_BLANK: // 空值   
	                	value = "";
	                    break;   
	                case HSSFCell.CELL_TYPE_ERROR: // 故障   
	                	value = "";   
	                    break;   
	                default:   
	                	value = "";    
	                    break;   
                }   
				
				String colName = rowNames.getCell(colIndex).getStringCellValue();
				//如果列在Excel中存在，单在KeyMap中不存在，就忽略该列。
				if(columnKeyMap.get(colName) == null) continue;
				item.put(columnKeyMap.get(colName), value);
			}
			data.add(item);
		}
		return data;
	}
	
	public static void createExcel(String filePath, Map<String, String> headers, List<Map<String, Object>> data) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();//创建Excel工作簿对象  
		HSSFSheet sheet = wb.createSheet("sheet1");//创建Excel工作表对象 
		
		HSSFRow head = sheet.createRow(0); //创建Excel工作表的表头
		int columnIndex = 0;
		for(String s : headers.keySet()){
			HSSFCell cell = head.createCell(columnIndex);
			cell.setCellValue(s);
			columnIndex++;
		}	
		
		for(int i = 1; i < data.size(); i++){
			HSSFRow row = sheet.createRow(i); //创建Excel工作表的行 
			columnIndex = 0;
			for(String s : headers.keySet()){
				HSSFCell cell = row.createCell(columnIndex);
				String key = headers.get(s);
				cell.setCellValue(data.get(i).get(key).toString());
				columnIndex++;
			}	
		}
		//FileOutputStream fileOut = new FileOutputStream(filePath);   
		//wb.write(fileOut);   
	}
	
	public static void main(String[] args) throws Exception{
		String excelFilePath = "d:/成品卡上传模板.xls";
		///List<Map<String, Object>> list = readExcel(excelFilePath);
		
		Map<String, String> colKeyMap = new HashMap<String, String>();
		colKeyMap.put("号码", "teleNo");
		colKeyMap.put("产品code", "productCode");
		colKeyMap.put("卡名", "cardName");      
		
		//colKeyMap.put("卡号", "cardNo");
		//colKeyMap.put("卡密", "cardPwd");
		//colKeyMap.put("状态", "state");
		//colKeyMap.put("面值", "cardPrice");
		//colKeyMap.put("售卖单号", "acceptId");
		//colKeyMap.put("售卖时间", "acceptTime");
		
		List<Map<String, Object>> list = readExcel(excelFilePath, colKeyMap);
		createExcel("d:/cc.xls",colKeyMap, list);
	}
}

