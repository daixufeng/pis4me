package com.pis.base;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserDaoTest{
	
	private final LocalServiceTestHelper helper =
	        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    // run this test twice to prove we're not leaking any state across tests
    @Test
    public void doTest() {
    	UserDao userDao = new UserDao();
    	SmUser user = new SmUser();
    	
    	user.setNickName("XuFeng");
    	user.setUserName("daixuf");
    	user.setPassword("882019");
    	user.setEmail("xufeng.dai@qq.com");
    	user.setType("admin");
    	
    	userDao.createUser(user);
    }
}
