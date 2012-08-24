package com.pis.util;

public class Util {
	public static String getWebClassesPath() {
		String path = new Util().getMyClass().getResource("/").getPath();
		return path;
	}

	private Class<?> getMyClass() {
		return this.getClass();
	}
}
