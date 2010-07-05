package com.pis.idao;

import java.util.List;

import com.pis.model.*;
public interface ICostDao {
	public void save(MCost cost);
	public void delete(MCost cost);
	public List<MCost> getCost(MCost cost);
}
