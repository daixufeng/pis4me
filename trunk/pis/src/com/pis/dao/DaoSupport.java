package com.pis.dao;

import com.pis.idao.*; 
import com.pis.model.MCost;
import com.pis.model.PMF;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class DaoSupport<T>{
	PersistenceManager pm ;
	
	public DaoSupport(){
		pm = PMF.get().getPersistenceManager();		
	}
	
	public T save(T entity){
		pm.currentTransaction().begin();
		try {
			pm.makePersistent(entity)
			pm.currentTransaction().commit();
			return entity;
		} catch (Exception ex) {
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		}finally {
			pm.close();
		}	
	}
	
	public void save(ICollection<T> entitys){		
		pm.currentTransaction().begin();
		try {
			pm.makePersistentAll(entitys);
			pm.currentTransaction().commit();
		} catch (Exception ex) {
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		}finally {
			pm.close();
		}	
	}
	
	public void delete(T entitys){
		pm.currentTransaction().begin();
		try {
			pm.deletePersistentAll(entitys);
			pm.currentTransaction().commit();
		} catch (Exception ex) {
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		}finally {
			pm.close();
		}	
	}
	
	public void delete(ICollection<T> entitys){
		pm.currentTransaction().begin();
		try {
			pm.deletePersistentAll(entitys);
			pm.currentTransaction().commit();
		} catch (Exception ex) {
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		}finally {
			pm.close();
		}	
	}
	
	public List<MKinds> getAll(){
		Query query = pm.newQuery(MKinds.class);
		List<MKinds> list = (List<MKinds>)query.execute();
		return list;
	}
}
