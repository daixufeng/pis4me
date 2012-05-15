package com.balian.app.dao;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.balian.app.TestSupport;
import com.balian.app.dao.SmUserDao;
import com.balian.app.domain.User;
import com.balian.app.service.SmService;

public class SmUserDaoTest extends TestSupport{
	

    public SmUserDaoTest(String testName) {
		super(testName);
		// TODO Auto-generated constructor stub
	}

	/**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SmUserDaoTest.class );
    }
	
	public void GetByUserId(){
		SmUserDao userDao = new SmUserDao();
		Long id = 2L;
		User user = userDao.getById(id);
		System.out.print(user.getEmail());
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
	public void testHibernate(){
		try{
			SmService smService = (SmService) getContext().getBean("smService");
			User user = new User();
			//user.setEmail("wangqin@qq.com");
			user.setUserName("xufeng");
			user.setPassword("11111");
			//smService.createUser(user);
			boolean flg = smService.doLogin(user);
			System.out.println(flg);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
}
