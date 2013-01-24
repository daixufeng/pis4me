package com.pis.domain;

import java.util.Map;

public class ViewPager {
	private int pageIndex;
	public int getPageIndex(){
		return this.pageIndex;
	}
	
	private int pageSize;
	public int getPageSize(){
		return pageSize;
	}
	
	private int pageCount;
	public int getPageCount(){
		return pageCount;
	}
	
	private String path;
	public String getPath(){
		return path;
	}
	
	private int totalCount;
	public int getTotalCount(){
		return totalCount;
	}
	
	private Map<String, Object> criteria;
	public String getParameters(){
		StringBuilder content = new StringBuilder();
		content.append("?");
		for(String str: criteria.keySet()){
			content.append(str);
			content.append("=");
			content.append(criteria.get(str));
			content.append("&");
		}
		
		return content.toString().substring(0, content.length() - 1);
	}
	
	public ViewPager(String path, int pageIndex, int pageSize, int dataCount, Map<String,Object> criteria){
		this.path = path;
		this.pageCount = dataCount / pageSize + ((dataCount % pageSize > 0) ? 1 : 0);
		this.totalCount = dataCount;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.criteria = criteria;
	}
	
	public String render(){
		StringBuilder content = new StringBuilder();

        if (getPageCount() == 0) 
        	return "<div style=\"width:100%; height:26px; color:red;text-align:center; padding:10px 10px 0px 10px; font-size:14px;\">Nothing found!</div>";

        content.append("<div style=\"width:100%; height:26px;\"><table style=\"float:right;\"><tr><td></td>");

        if (getPageIndex() > 1)
        {
        	//content.append("<td style=\"padding:0px;\"><a title=\"第一页\" style=\"display:block;height:16px; width:16px; text-decoration:none;background:url(/images/gridview-pager.png) no-repeat 1px -2px;\" href=\"" + getPath() + "/1" + "\"></a></td>");
        	content.append("<td style=\"padding:0px;\"><a title=\"第一页\" style=\"display:block;height:16px; width:16px; text-decoration:none;background:url(/images/gridview-pager.png) no-repeat 1px -2px;\" href=\"");
        	content.append(getPath());
        	content.append("/1");
        	content.append(getParameters());
        	content.append("\"></a></td>");
        	//content.append("<td style=\"padding:0px;\"><a title=\"前一页\" style=\"display:block;height:16px; width:16px; text-decoration:none;background:url(/images/gridview-pager.png) no-repeat -96px -2px;\" href=\"" +  getPath() + "/" + (getPageIndex() - 1) + "\"></a></td>");
        	content.append("<td style=\"padding:0px;\"><a title=\"前一页\" style=\"display:block;height:16px; width:16px; text-decoration:none;background:url(/images/gridview-pager.png) no-repeat -96px -2px;\" href=\"");
        	content.append(getPath());
        	content.append("/");
        	content.append(getPageIndex() - 1);
        	content.append(getParameters());
        	content.append("\"></a></td>");
        }
        else
        {
        	content.append("<td style=\"padding:0px;\"><a title=\"第一页\" style=\"display:block;height:16px; width:16px; text-decoration:none;background:url(/images/gridview-pager.png) no-repeat -16px -2px;\" href=\"#\"></a></td>");
        	content.append("<td style=\"padding:0px;\"><a title=\"前一页\" style=\"display:block;height:16px; width:16px; text-decoration:none;background:url(/images/gridview-pager.png) no-repeat -111px -2px;\" href=\"#\"></a></td>");
        }

        content.append("<td style=\"text-align:center; padding:0px 10px 0px 10px;\">" + getPageIndex() + "&nbsp;&nbsp;/&nbsp;&nbsp;" + getPageCount() + "页</td>");

        if (getPageIndex() < getPageCount())
        {
        	content.append("<td style=\"padding:0px;\"><a title=\"下一页\" style=\"display:block;height:16px; width:16px; text-decoration:none;background:url(/images/gridview-pager.png) no-repeat -65px -2px;\" href=\"" + getPath() + "/" + (getPageIndex() + 1) + getParameters() + "\"></a></td>");
        	content.append("<td style=\"padding:0px;\"><a title=\"最后一页\" style=\"display:block;height:16px; width:16px; text-decoration:none;background:url(/images/gridview-pager.png) no-repeat -33px -2px;\" href=\"" + getPath() + "/" + getPageCount() + getParameters() + "\"></a></td>");
        }
        else
        {
        	content.append("<td style=\"padding:0px;\"><a title=\"下一页\"  style=\"display:block;height:16px; width:16px; text-decoration:none;background:url(/images/gridview-pager.png) no-repeat -80px -2px;\" href=\"#\"></a></td>");
        	content.append("<td style=\"padding:0px;\"><a title=\"最后一页\"  style=\"display:block;height:16px; width:16px; text-decoration:none;background:url(/images/gridview-pager.png) no-repeat -50px -2px;\" href=\"#\"></a></td>");
        }

        content.append("<td style=\"padding:0px 0px 0px 10px;\"><a title=\"Refresh\" style=\"display:block;height:22px; width:22px; text-decoration:none;background:url(/images/gridview-pager.png) no-repeat -136px 2px;\" href=\"" + getPath() + "/" + getPageIndex() + "/" + getParameters()  + "\"></a></td>");
        
        content.append("<td style=\"text-align:center; padding:0px 10px 0px 10px;\">共：" + getTotalCount() + "&nbsp;条&nbsp;</td>");
        
        content.append("</tr></table></div>");
		return content.toString();
	}
}
