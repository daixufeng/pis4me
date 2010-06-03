package com.pis.dao;

import com.pis.idao.*; 
import com.pis.model.MCost;
import com.pis.model.PMF;

import java.util.List;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;


public class CostDao implements ICostDao {
	private static final PersistenceManagerFactory pmfInstance = JDOHelper
	.getPersistenceManagerFactory("transactions-optional");

	public static PersistenceManagerFactory getPersistenceManagerFactory() {
		return pmfInstance;
	}

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
		String query = "select from " + MCost.class.getName();
		return (List<MCost>) pm.newQuery(query).execute();
	}

	public void delete(MCost cost) {
		PersistenceManager pm = getPersistenceManagerFactory()
				.getPersistenceManager();
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

