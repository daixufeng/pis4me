/**
 * 
 */
package com.pis.common.web.freemarker.directive;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.AbstractTemplateView;

import com.pis.common.web.freemarker.exception.MustStringException;

import freemarker.core.Environment;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;

/**
 * @author Qiuxj
 */
public class DirectiveUtils {

	public static final String OUT_BEAN = "tag_bean";
	public static final String OUT_LIST = "tag_list";
	public static final String OUT_PAGINATION = "tag_pagination";
	public static final String PARAM_TPL = "tpl";
	public static final String PARAM_TPL_SUB = "tplSub";
	static Log log = LogFactory.getLog(DirectiveUtils.class);

	public static Map<String, TemplateModel> addParamsToVariable(Environment env, Map<String, TemplateModel> params)
			throws TemplateException {
		Map<String, TemplateModel> origMap = new HashMap<String, TemplateModel>();
		if (params.size() <= 0) {
			return origMap;
		}
		Set<Entry<String, TemplateModel>> entrySet = params.entrySet();

		for (Entry<String, TemplateModel> entry : entrySet) {
			String key = (String) entry.getKey();
			TemplateModel value = env.getVariable(key);
			if (value != null) {
				origMap.put(key, value);
			}
			env.setVariable(key, (TemplateModel) entry.getValue());
		}
		return origMap;
	}

	public static void removeParamsFromVariable(Environment env, Map<String, TemplateModel> params,
			Map<String, TemplateModel> origMap) throws TemplateException {
		if (params.size() <= 0) {
			return;
		}
		for (String key : params.keySet())
			env.setVariable(key, (TemplateModel) origMap.get(key));
	}

	public static RequestContext getContext(Environment env) throws TemplateException {
		TemplateModel ctx = env.getGlobalVariable(AbstractTemplateView.SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE);
		if ((ctx instanceof AdapterTemplateModel)) {
			return (RequestContext) ((AdapterTemplateModel) ctx).getAdaptedObject(RequestContext.class);
		}
		throw new TemplateModelException("RequestContext 'springMacroRequestContext' not found in DataModel.");
	}

	public static String getString(String name, Map<String, TemplateModel> params) throws Exception {
		TemplateModel model = (TemplateModel) params.get(name);
		if (model == null) {
			return null;
		}
		if ((model instanceof TemplateScalarModel))
			return ((TemplateScalarModel) model).getAsString();
		if ((model instanceof TemplateNumberModel)) {
			return ((TemplateNumberModel) model).getAsNumber().toString();
		}
		throw new MustStringException(name);
	}

	public static Long getLong(String name, Map<String, TemplateModel> params) throws Exception {
		TemplateModel model = (TemplateModel) params.get(name);
		if (model == null) {
			return null;
		}
		if ((model instanceof TemplateScalarModel)) {
			String s = ((TemplateScalarModel) model).getAsString();
			if (StringUtils.isBlank(s))
				return null;
			try {
				return Long.valueOf(Long.parseLong(s));
			} catch (NumberFormatException e) {
				throw new Exception(name);
			}
		}
		if ((model instanceof TemplateNumberModel)) {
			return Long.valueOf(((TemplateNumberModel) model).getAsNumber().longValue());
		}
		throw new Exception(name);
	}

	public static Number getNumber(String name, Map<String, TemplateModel> params) {
		TemplateModel model = (TemplateModel) params.get(name);
		if (model == null) {
			return null;
		}
		if ((model instanceof TemplateNumberModel)) {
			try {
				return ((TemplateNumberModel) model).getAsNumber();
			} catch (TemplateModelException e) {
				log.error(e.getMessage(), e);
			}
		}
		return null;
	}

	public static Integer getInt(String name, Map<String, TemplateModel> params) throws Exception {
		TemplateModel model = (TemplateModel) params.get(name);
		if (model == null) {
			return null;
		}
		if ((model instanceof TemplateScalarModel)) {
			String s = ((TemplateScalarModel) model).getAsString();
			if (StringUtils.isBlank(s))
				return null;
			try {
				return Integer.valueOf(Integer.parseInt(s));
			} catch (NumberFormatException e) {
				throw new Exception(name);
			}
		}
		if ((model instanceof TemplateNumberModel)) {
			return Integer.valueOf(((TemplateNumberModel) model).getAsNumber().intValue());
		}
		throw new Exception(name);
	}

	public static Integer[] getIntArray(String name, Map<String, TemplateModel> params) throws Exception {
		String str = getString(name, params);
		if (StringUtils.isBlank(str)) {
			return null;
		}
		String[] arr = StringUtils.split(str, ',');
		Integer[] ids = new Integer[arr.length];
		int i = 0;
		try {
			for (String s : arr) {
				ids[(i++)] = Integer.valueOf(s);
			}
			return ids;
		} catch (Exception e) {
			throw new Exception(name, e);
		}

	}

	public static Boolean getBool(String name, Map<String, TemplateModel> params) throws Exception {
		TemplateModel model = (TemplateModel) params.get(name);
		if (model == null) {
			return null;
		}
		if ((model instanceof TemplateBooleanModel))
			return Boolean.valueOf(((TemplateBooleanModel) model).getAsBoolean());
		if ((model instanceof TemplateNumberModel))
			return Boolean.valueOf(((TemplateNumberModel) model).getAsNumber().intValue() != 0);
		if ((model instanceof TemplateScalarModel)) {
			String s = ((TemplateScalarModel) model).getAsString();

			if (!StringUtils.isBlank(s)) {
				return Boolean
						.valueOf((!s.equals("0")) && (!s.equalsIgnoreCase("false")) && (!s.equalsIgnoreCase("f")));
			}
			return null;
		}

		throw new Exception(name);
	}

	// public static Date getDate(String name, Map<String, TemplateModel> params)
	// throws Exception {
	// TemplateModel model = (TemplateModel) params.get(name);
	// if (model == null) {
	// return null;
	// }
	// if ((model instanceof TemplateDateModel))
	// return ((TemplateDateModel) model).getAsDate();
	// if ((model instanceof TemplateScalarModel)) {
	// DateTypeEditor editor = new DateTypeEditor();
	// editor.setAsText(((TemplateScalarModel) model).getAsString());
	// return (Date) editor.getValue();
	// }
	// throw new Exception(name);
	// }

	public static InvokeType getInvokeType(Map<String, TemplateModel> params) throws Exception {
		String tpl = getString("tpl", params);
		if ("3".equals(tpl))
			return InvokeType.userDefined;
		if ("2".equals(tpl))
			return InvokeType.sysDefined;
		if ("1".equals(tpl)) {
			return InvokeType.custom;
		}
		return InvokeType.body;
	}

	public static enum InvokeType {
		body, custom, sysDefined, userDefined;
	}

}
