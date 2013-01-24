package com.pis.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.datastore.Entity;
import com.pis.util.XmlUtil;

public class EntityFactory {

	/**
	 * get an entity from map
	 * 
	 * @param entityMap
	 * @return
	 */
	public static Entity getEntityFromMap(Map<String, Object> entityMap,
			Class<?> myClass) {
		Entity entity = new Entity(myClass.getSimpleName());

		List<Map<String, String>> properties = XmlUtil
				.getEntityProperties(myClass);

		for (int i = 1; i < properties.size(); i++) {
			String name = properties.get(i).get("name");
			String type = properties.get(i).get("type").toLowerCase();
			String columnName = name;
			Object value = null;

			if (entityMap.get(name) == null)
				break;
			String oVal = entityMap.get(name).toString();

			if (type.equals("long"))
				value = Long.parseLong(oVal.toString());
			else if (type.equals("date")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					value = sdf.parse(oVal);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (type.equals("double"))
				value = Double.parseDouble(oVal);
			else
				value = oVal;

			if (value != null)
				entity.setProperty(columnName, value);
		}

		return entity;
	}

	/**
	 * 
	 * @param request
	 * @return com.google.appengine.api.datastore.Entity
	 */
	public static MyEntity getEntityFormRequest(HttpServletRequest request,
			Class<?> myClass) {
		MyEntity result = new MyEntity();
		Entity entity = new Entity(myClass.getSimpleName());
		boolean validation = true;
		Map<String, String> messages = new HashMap<String, String>();

		List<Map<String, String>> properties = XmlUtil
				.getEntityProperties(myClass);

		for (int i = 0; i < properties.size(); i++) {
			Map<String, String> schema = properties.get(i);
			String name = schema.get("name");
			String notNull = schema.get("not-null").toLowerCase();
			String description = schema.get("description");

			String propertyName = name.toLowerCase();
			String columnName = name;
			String oVal = request.getParameter(propertyName);
			if (oVal == null || oVal == "") {
				if (notNull != null && notNull.equals("true")) {
					messages.put(name, description + " is required.");
					validation = false;
				}
				continue;
			}

			Object value = null;
			try {
				value = castValue(schema, oVal);
			} catch (Exception ex) {
				validation = false;
				messages.put(name, ex.getMessage());
			}
			if (value != null)
				entity.setProperty(columnName, value);
		}
		result.entity = entity;
		result.validation = validation;
		result.messages = messages;
		return result;
	}

	/**
	 * 
	 * @param request
	 * @return java.util.Map query criteria
	 */
	public static Map<String, Object> getCriteriaFromRequest(
			HttpServletRequest request, Class<?> myClass) {
		Map<String, Object> item = new HashMap<String, Object>();

		List<Map<String, String>> properties = XmlUtil
				.getEntityCriteria(myClass);

		for (int i = 0; i < properties.size(); i++) {
			String name = properties.get(i).get("name");
			String type = properties.get(i).get("type").toLowerCase();
			String propertyName = name.toLowerCase();

			String oVal = request.getParameter(propertyName);
			if (oVal == null || oVal == "")
				continue;

			Object value = castValue(type, oVal);
			if (value != null)
				item.put(propertyName, value);
		}
		return item;
	}

	/**
	 * copy entity
	 * 
	 * @return
	 */
	public static Entity copyEntity(Entity source, Entity target,
			boolean isWithKey) {
		Map<String, Object> properties = source.getProperties();
		Set<String> keySet = properties.keySet();

		for (String k : keySet) {
			target.setProperty(k, properties.get(k));
		}
		return target;
	}

	/**
	 * copy entity with property name
	 * 
	 * @param entity
	 * @param keys
	 * @return
	 */
	public static Entity copyEntity(Entity source, Entity target, String[] keys) {
		Map<String, Object> properties = source.getProperties();
		for (String key : keys) {
			target.setProperty(key, properties.get(key));
		}
		return target;
	}

	/**
	 * convert entity to Map
	 * 
	 * @param com
	 *            .google.appengine.api.datastore.Entity entity
	 * @return java.util.Map item
	 */
	public static Map<String, Object> entityToMap(Entity entity) {
		Map<String, Object> item = new HashMap<String, Object>();
		Map<String, Object> o = entity.getProperties();
		for (String str : o.keySet())
			item.put(str, o.get(str));

		Long id = entity.getKey().getId();
		item.put("Id", id);
		return item;
	}

	/**
	 * do cast
	 * 
	 * @param type
	 * @param oValue
	 * @return
	 */
	private static Object castValue(String type, String oValue) {
		Object value = null;

		if (type.equals("long"))
			value = Long.parseLong(oValue.toString());
		else if (type.equals("double"))
			value = Double.parseDouble(oValue);
		else if (type.equals("int"))
			value = Integer.parseInt(oValue);
		else if (type.equals("date")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				value = sdf.parse(oValue);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			value = oValue;
		}
		return value;
	}

	/**
	 * do cast include validate
	 * 
	 * @param schema
	 * @param oValue
	 * @return
	 * @throws Exception
	 */
	private static Object castValue(Map<String, String> schema, String oValue)
			throws Exception {
		String msg = validate(schema, oValue);
		if (msg != "")
			throw new Exception(msg);
		return castValue(schema.get("type").toLowerCase(), oValue);
	}

	/**
	 * validate
	 * 
	 * @param schema
	 * @param oValue
	 * @return
	 */
	private static String validate(Map<String, String> schema, String oValue) {
		String message = "";
		String description = schema.get("description");

		String len = schema.get("length");
		if (len != null) {
			len = len.substring(1);
			if (len.contains(",")) {
				len = len.substring(0, len.length() - 1);
				int min = Integer.parseInt(len.split(",")[0]);
				int max = Integer.parseInt(len.split(",")[1]);

				if (oValue.length() < min)
					return description + " is less than min length.";
				if (oValue.length() > max)
					return description + " is less than min length.";
			}
		}
		String validation = schema.get("validation");
		if (validation != null && validation != "") {
			String regexString = XmlUtil.getRegexString(validation);
			Pattern pattern = Pattern.compile(regexString);
			Matcher matcher = pattern.matcher(oValue);
			if (!matcher.matches())
				return description + " is invalid.";
		}
		return message;
	}
}
