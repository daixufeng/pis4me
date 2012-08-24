package com.pis.util;

import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.pis.TestSupport;
import com.pis.domain.MyEntities;

public class XmlUtilTest extends TestSupport {
	 public XmlUtilTest(String testName) {
		super(testName);
		// TODO Auto-generated constructor stub
	}

		/**
	     * @return the suite of tests being tested
	     */
	    public static Test suite()
	    {
	        return new TestSuite( XmlUtilTest.class );
	    }
		
		public void testGetByUserId(){
			List<Map<String,String>> list = XmlUtil.getEntityCriteria(MyEntities.DailyPay.class);
			System.out.println(list);
		}
		/*
		public void testGetByUserName()
	    {
			SmUserDao userDao = new SmUserDao();
			
			User user = userDao.getByUserName("xufeng");
			//User user = userDao.getById(2);
			System.out.print(user.getEmail());
	        assertTrue( true );
	    }
		*/
}
