package com.pis.domain;

import java.util.Map;

import com.google.appengine.api.datastore.Entity;

public class MyEntity {
	public Entity entity;
	public Map<String, String> messages;
	public boolean validation = true;
}
