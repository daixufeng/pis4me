package com.pis.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlUtil {
	private static String CLASS_PATH = "com/pis/domain/";
	private static String XML_EXTENSION = ".xml";

	/**
	 * get Entity properties from XML Configuration
	 * 
	 * @param myClass
	 * @return
	 */
	public static List<Map<String, String>> getEntityProperties(Class<?> myClass) {
		List<Map<String, String>> properties = new ArrayList<Map<String, String>>();

		Element root = getEntityRoot(myClass);

		@SuppressWarnings("unchecked")
		Iterator<Element> elt = root.elementIterator("properties");

		if (elt.hasNext()) {
			Element el = elt.next();
			@SuppressWarnings("unchecked")
			Iterator<Element> pro = el.elementIterator("property");
			while (pro.hasNext()) {
				Element o = pro.next();
				Map<String, String> item = new HashMap<String, String>();

				item.put("name", o.attributeValue("name"));

				if (o.attributeValue("type") != null)
					item.put("type", o.attributeValue("type"));
				else
					item.put("type", "String");

				if (o.attributeValue("not-null") != null)
					item.put("not-null", o.attributeValue("not-null"));
				else
					item.put("not-null", "false");

				if (o.attributeValue("description") != null)
					item.put("description", o.attributeValue("description"));
				else
					item.put("description", o.attributeValue("name"));

				if (o.attributeValue("length") != null)
					item.put("length", o.attributeValue("length"));

				if (o.attributeValue("validation") != null)
					item.put("validation", o.attributeValue("validation"));

				properties.add(item);
			}
		}
		return properties;
	}

	/**
	 * get Entity properties from XML Configuration
	 * 
	 * @param myClass
	 * @return
	 */
	public static List<Map<String, String>> getEntityCriteria(Class<?> myClass) {
		List<Map<String, String>> properties = new ArrayList<Map<String, String>>();

		Element root = getEntityRoot(myClass);

		@SuppressWarnings("unchecked")
		Iterator<Element> elt = root.elementIterator("criteria");

		if (elt.hasNext()) {
			Element el = elt.next();
			@SuppressWarnings("unchecked")
			Iterator<Element> pro = el.elementIterator("property");
			while (pro.hasNext()) {
				Element o = pro.next();
				Map<String, String> item = new HashMap<String, String>();

				// name
				item.put("name", o.attributeValue("name"));

				// type
				if (o.attributeValue("type") != null)
					item.put("type", o.attributeValue("type"));
				else
					item.put("type", "String");
				properties.add(item);
			}
		}
		return properties;
	}

	/**
	 * get regex string from configuration
	 * @param name
	 * @return
	 */
	public static String getRegexString(String name) {
		String regexString = "";
		Element root = getXmlDocRoot("Validation");
		@SuppressWarnings("unchecked")
		Iterator<Element> iter = root.elementIterator("properties");
		if (iter.hasNext()) {
			Element el = iter.next();
			@SuppressWarnings("unchecked")
			Iterator<Element> pro = el.elementIterator("property");
			while (pro.hasNext()) {
				Element o = pro.next();
				if(name.equals(o.attributeValue("name"))){
					regexString = o.getTextTrim();
					break;
				}
			}
		}
		return regexString;
	}

	private static Element getEntityRoot(Class<?> myClass) {
		String className = myClass.getSimpleName();
		// get class root path
		return getXmlDocRoot(className);
	}

	private static Element getXmlDocRoot(String simpleFileName) {
		// get class root path
		String path = Util.getWebClassesPath();
		StringBuilder fileName = new StringBuilder(path);
		fileName.append(CLASS_PATH);
		fileName.append(simpleFileName);
		fileName.append(XML_EXTENSION);

		Logger logger = Logger.getLogger(XmlUtil.class.getName());

		SAXReader saxReader = new SAXReader();
		Document doc = null;
		try {
			doc = saxReader.read(new File(fileName.toString()));
		} catch (DocumentException e) {
			logger.error(e.getMessage());
		}
		Element root = doc.getRootElement();
		return root;
	}
}
