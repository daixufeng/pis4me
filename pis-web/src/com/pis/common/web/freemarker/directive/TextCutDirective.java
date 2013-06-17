/**
 * 
 */
package com.pis.common.web.freemarker.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import com.pis.common.util.StrUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Xufeng Dai
 * 
 */
@SuppressWarnings("unchecked")
public class TextCutDirective implements TemplateDirectiveModel {

	public static final String PARAM_S = "s";
	public static final String PARAM_LEN = "len";
	public static final String PARAM_APPEND = "append";

	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String s = null;
		Integer len = null;
		String append = null;
		try {
			s = DirectiveUtils.getString("s", params);
			len = DirectiveUtils.getInt("len", params);
			append = DirectiveUtils.getString("append", params);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (s != null) {
			Writer out = env.getOut();
			if (len != null)
				out.append(StrUtils.textCut(s, len.intValue(), append));
			else
				out.append(s);
		}
	}

}
