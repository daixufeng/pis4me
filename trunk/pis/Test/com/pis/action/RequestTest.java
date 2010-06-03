package com.pis.action;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class RequestTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void Test() {
		Request re = new Request("namespace/classname/methodName.action?wq9r789qw7r98q7w89e");
		System.out.println("ClassName:" + re.getClassName());
		System.out.println("MethodName:" + re.getMethodName());
	}
}
