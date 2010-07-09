package com.pis.idao;

import java.util.List;

import com.pis.model.*;

public interface IKindsDao {
	public void save(List<MKinds> kinds);
	public void delete(List<MKinds> kinds);
	public List<MKinds> getCost(MKinds kind);
}
