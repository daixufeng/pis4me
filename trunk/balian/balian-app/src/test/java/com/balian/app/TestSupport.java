package com.balian.app;

import junit.framework.TestCase;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSupport  extends TestCase{
	private ApplicationContext context;	

	public TestSupport( String testName )
    {
        super(testName);
    }
	
	@BeforeClass
	public ApplicationContext getContext() {
		//加载文件系统下的路径中的配置文件
		if(context == null){
			//context = new ClassPathXmlApplicationContext(new String[]{
				//"classpath*:/spring/spring-dao.xml", 
				//"classpath*:/spring/spring-hibernate.xml", 
				//"classpath*:/spring/spring-resource.xml", 
				//"classpath*:/spring/spring-service.xml"});
			context = new ClassPathXmlApplicationContext("classpath*:/spring/*.xml");
		}
		return context;
	}
	
//加载单个spring文件
//ApplicationContext context00  = new ClassPathXmlApplicationContext("spring-global-db.xml");
  
//加载类路径下的配置文件ClassPathXmlApplicationContex
//ApplicationContext context0 = new ClassPathXmlApplicationContext(
//new String[]{"spring-global-db.xml",
//"spring-dao.xml",
//"spring-service.xml"});
	
//加载文件系统下的路径中的配置文件
//String basePath = System.getProperty("user.dir");
//String db = basePath+"/WebRoot/WEB-INF/spring/spring-global-db.xml";
//String dao = basePath+"\\WebRoot\\WEB-INF\\spring\\spring-dao.xml";
//String service = basePath+"\\WebRoot\\WEB-INF\\spring\\spring-service.xml";
//ApplicationContext context1 =
//new FileSystemXmlApplicationContext(new String[]{db, dao, service});
  
//用WEB应用的路径加载spring配置文件
//XmlWebApplicationContext context2 = new XmlWebApplicationContext();
//context2.setConfigLocations(new String[]{
//"/WEB-INF/spring/spring-global-db.xml",
// "/WEB-INF/spring/spring-dao.xml",
//"/WEB-INF/spring/spring-service.xml"
//});
	
//需要servletContext变量，一般由request.getSession().getSersvletContext()获取
//context2.setServletContext(request.getSession().getServletContext());
//context2.refresh();
  
//manageEmpServiceImpl = (ManageEmpServiceImpl) context1.getBean("manageEmpServiceImpl");
	  
}
