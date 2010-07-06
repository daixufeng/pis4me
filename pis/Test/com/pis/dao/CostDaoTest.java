package com.pis.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.jdo.PersistenceManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mortbay.log.Log;

import com.pis.model.*;

public class CostDaoTest{
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void saveTest(){
		MCost cost = new MCost();
		cost.setCost(10);
		cost.setCostDate("20110506");
		cost.setKind("TTT");
		cost.setRemark("CSDFSD");
		
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		pm.makePersistent(cost);
		//Query query = pm.newQuery(MCost.class);
		
		Log.debug("OK");
	}
	@Test
	public void getTest(){
		CostDao dao = new CostDao();
		MCost cost = new MCost();
		List<MCost> list = dao.getCost(cost);
		System.out.println(list.get(0).getKind());
		//System.out.println("Hello!");
		assertTrue(true);
	}
}