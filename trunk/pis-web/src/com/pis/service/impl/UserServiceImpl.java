package com.pis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.pis.domain.MyEntities;
import com.pis.domain.Page;
import com.pis.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseService implements UserService {

	@Override
	public Map<String, Object> getById(Long id) {
		return super.getById(id);
	}

	@Override
	public Map<String, Object> create(Entity category) {
		return super.create(category);
	}

	@Override
	public Map<String, Object> update(Entity category) {
		return super.update(category);
	}

	@Override
	public void delete(Entity category) {
		super.delete(category);
	}

	@Override
	public void delete(List<Entity> categories) {
		super.delete(categories);
	}

	@Override
	public List<Map<String, Object>> getAll() {		
		//Entity p = EntityFactory.getEntityFromMap(new HashMap<String,Object>(), EntityField.Dictionary.class);
		
		return super.getAll();
	}

	@Override
	public List<Map<String, Object>> find(Map<String, Object> filterMap, Map<String, Object> likeMap,
			Map<String, Object> sortMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page getPageData(int page, int pageSize, Map<String, Object> filterMap, 
			Map<String, Object> likeMap, Map<String, Object> sortMap) {
		return super.getPageData(filterMap, likeMap, sortMap, page, pageSize);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Map<String, Object> getUserByName(String userName) {
		Query q = new Query("User");
		q.addFilter("UserName", Query.FilterOperator.EQUAL, userName);
						
		PreparedQuery pq = datastore.prepare(q);
		Entity entity = pq.asSingleEntity();
		
		return entity.getProperties();
	}

	@Override
	public boolean doLogin(Map<String,Object> user) {
		Map<String,Object> logonUser = this.getUserByName(user.get("username").toString());
		if(logonUser != null && logonUser.get("password").equals(user.get("password")))
			return true;
		else
			return false;
	}

	@Override
	protected String getEntityName() {
		return MyEntities.User.class.getSimpleName();
	}

}
