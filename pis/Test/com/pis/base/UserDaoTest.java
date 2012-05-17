package com.pis.base;

import java.util.HashMap;
import java.util.Map;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.pis.service.SmService;

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
    	SmService smService = new SmService();
    	Map<String,Object> user = new HashMap<String,Object>();
    	
		user.put("nickname","XuFeng");
		user.put("username","daixuf");
		user.put("password","882019");
		user.put("email","xufeng.dai@qq.com");
		user.put("type","admin");
    	
    	smService.createUser(user);
    }
}
