/**
 * Copyright 2011-2012. Chongqing Crun Information & Network Co., Ltd. All rights reserved.
 * <a>http://www.crunii.com</a>
 */
package com.pis.common.web.mvc.view;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 * @author Qiuxj create 2012-12-16
 * 
 */
public class PisExcelView extends AbstractExcelView {
	public static final String XLS_MODEL_KEY = "XLS_SHEET";//导出单个sheet时的对象KEY
	public static final String XLS_MODEL_KEY_SHEETS = "XLS_SHEETS";//导出多个sheet时的对象KEY
	public static final String XLS_SHEET_NAME_KEY = "XLS_SHEET_NAME";//sheet名称KEY
	public static final String DEFAULE_SHEET_NAME = "sheet";//默认sheet名称
	public static final String DEFAULE_FILE_NAME = "NONE-NAME";//默认sheet名称
	private static final String EXTENSION = ".xls";
	private String fileName;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public PisExcelView() {		
	}
	
	public PisExcelView(String fileName) {
		this.fileName = fileName;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		setHeader(response);
		if(model.containsKey(XLS_MODEL_KEY)){//导出单个sheet
			List<Map<String, Object>> sheetList = (List<Map<String,Object>>)model.get(XLS_MODEL_KEY);
			this.createSheet(model.get(XLS_SHEET_NAME_KEY) == null? null : model.get(XLS_SHEET_NAME_KEY).toString(), sheetList, workbook);
			return;
		}

		if(model.containsKey(XLS_MODEL_KEY_SHEETS)){//导出多个sheet
			List<Map<String, Object>> sheetsList = (List<Map<String,Object>>)model.get(XLS_MODEL_KEY_SHEETS);
			for(Map<String, Object> sheet : sheetsList){
				List<Map<String, Object>> sheetList = (List<Map<String,Object>>)sheet.get(XLS_MODEL_KEY);
				this.createSheet(sheet.get(XLS_SHEET_NAME_KEY) == null? null : sheet.get(XLS_SHEET_NAME_KEY).toString(), sheetList, workbook);
			}
			return;
		}
	}
	
	@SuppressWarnings({ "deprecation" })
	private void createSheet(String sheetName, List<Map<String, Object>> sheetList, HSSFWorkbook workbook) {
		if(StringUtils.isBlank(sheetName)){
			sheetName = DEFAULE_SHEET_NAME;
		}
		
		HSSFSheet sheet = workbook.createSheet(sheetName);
		
		Iterator<Map<String, Object>> sheetIt = sheetList.iterator();
		
		int i = 0;
		Set<String> keys = null;
		while(sheetIt.hasNext()){
			HSSFRow row = sheet.createRow(i++);
			Map<String, Object> item = (Map<String, Object>)sheetIt.next();
			if(keys == null){
				keys = item.keySet();
			}
			
			int j = 0;
			for(String key : keys){
				HSSFCell cell = row.createCell((short)j++);
				Object value = item.get(key);
				if(value instanceof Integer){
					cell.setCellValue((Integer)value);
				}else if(value instanceof String){
					cell.setCellValue((String)value);
				}else if(value instanceof Double){
					cell.setCellValue((Double)value);
				}else if(value instanceof Boolean){
					cell.setCellValue((Boolean)value);
				}else if(value instanceof Date){
					//cell.setCellValue((Date)value);
					cell.setCellValue(sdf.format(value));
				}else if(value instanceof Calendar){
					cell.setCellValue((Calendar)value);
				}else if(value instanceof BigDecimal){
					cell.setCellValue(((Number)value).longValue());
				}else if(value instanceof Boolean){
					cell.setCellValue((Boolean)value);
				}else{
					cell.setCellValue(value == null ? "" : value.toString());
				}
			}
		}
	}
	
	private void setHeader(HttpServletResponse response){
		response.setHeader("Content-disposition", "attachment;filename=" + this.getFileName() + EXTENSION); 
	}
	
	private String getFileName(){
		return StringUtils.isBlank(this.fileName) ? DEFAULE_FILE_NAME : this.fileName;
	}
	
}
