package com.pis.dao;

import com.pis.idao.*;
import com.pis.model.MKinds;
import com.pis.model.PMF;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class KindsDao extends DaoSupport implements IKindsDao {

	public List<MKinds> getKinds(MKinds kind) {
		Query query = pm.newQuery(MKinds.class);
		query.declareParameters("String @Type, String @Property");

		StringBuilder filter = new StringBuilder();

		if (kind.getType() != null && !kind.getType().equals("")) {
			if (filter.length() != 0) {
				filter.append(" && ");
			}
			filter.append("Type == @Type");
		}

		if(kind.getProperty() != null && !kind.getProperty().equals("")){
			if (filter.length() != 0) {
				filter.append(" && ");
			}
			filter.append(" Property == @Property ");			
		}

		List<MKinds> list = (List<MKinds>) query.execute(kind.getType(),kind.getProperty());
		return list;
	}
}
