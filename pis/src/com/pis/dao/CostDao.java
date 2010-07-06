package com.pis.dao;

import com.pis.idao.*; 
import com.pis.model.MCost;
import com.pis.model.PMF;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


public class CostDao implements ICostDao {

	@Override
	public void save(MCost cost) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(cost);
		} catch (Exception ex) {
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		}finally {
			pm.close();
		}	
	}	

	@SuppressWarnings("unchecked")
	public List<MCost> getCost(MCost cost) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
	
		Query query = pm.newQuery(MCost.class);
	    //query.setFilter("costId >= CostId");
	    //query.declareParameters("Long CostId");
		List<MCost> list = (List<MCost>)query.execute();
		//pm.close();
		return list;
	}

	public void delete(MCost cost) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
				
		try {
			pm.currentTransaction().begin();

			// We don't have a reference to the selected Product.
			// So we have to look it up first,
			cost = pm.getObjectById(MCost.class, cost.getCostId());
			pm.deletePersistent(cost);

			pm.currentTransaction().commit();
		} catch (Exception ex) {
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		} finally {
			pm.close();
		}
	}

}

