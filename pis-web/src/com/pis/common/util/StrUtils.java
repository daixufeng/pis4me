/**
 * 
 */
package com.pis.common.util;

import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.util.ParserException;

/**
 * @author Xufeng Dai
 * 
 */
public abstract class StrUtils {
	private static final String DELIM_START = "${";
	private static final String DELIM_STOP = "}";

	public static String handelUrl(String url) {
		if (url == null) {
			return null;
		}
		url = url.trim();
		if ((url.equals("")) || (url.startsWith("http://"))
				|| (url.startsWith("https://"))) {
			return url;
		}
		return "http://" + url.trim();
	}

	public static String[] splitAndTrim(String str, String sep, String sep2) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		if (!StringUtils.isBlank(sep2)) {
			str = StringUtils.replace(str, sep2, sep);
		}
		String[] arr = StringUtils.split(str, sep);

		int i = 0;
		for (int len = arr.length; i < len; i++) {
			arr[i] = arr[i].trim();
		}
		return arr;
	}

	public static String txt2htm(String txt) {
		if (StringUtils.isBlank(txt)) {
			return txt;
		}
		StringBuilder sb = new StringBuilder((int) (txt.length() * 1.2D));

		boolean doub = false;
		for (int i = 0; i < txt.length(); i++) {
			char c = txt.charAt(i);
			if (c == ' ') {
				if (doub) {
					sb.append(' ');
					doub = false;
				} else {
					sb.append("&nbsp;");
					doub = true;
				}
			} else {
				doub = false;
				switch (c) {
				case '&':
					sb.append("&amp;");
					break;
				case '<':
					sb.append("&lt;");
					break;
				case '>':
					sb.append("&gt;");
					break;
				case '"':
					sb.append("&quot;");
					break;
				case '\n':
					sb.append("<br/>");
					break;
				default:
					sb.append(c);
				}
			}
		}

		return sb.toString();
	}

	public static String textCut(String s, int len, String append) {
		if (s == null) {
			return null;
		}
		int slen = s.length();
		if (slen <= len) {
			return s;
		}

		int maxCount = len * 2;
		int count = 0;
		int i = 0;
		for (; (count < maxCount) && (i < slen); i++) {
			if (s.codePointAt(i) < 256)
				count++;
			else {
				count += 2;
			}
		}
		if (i < slen) {
			if (count > maxCount) {
				i--;
			}
			if (!StringUtils.isBlank(append)) {
				if (s.codePointAt(i - 1) < 256)
					i -= 2;
				else {
					i--;
				}
				return s.substring(0, i) + append;
			}
			return s.substring(0, i);
		}

		return s;
	}

	public static String htmlCut(String s, int len, String append) {
		String text = html2Text(s, len * 2);
		return textCut(text, len, append);
	}

	public static String html2Text(String html, int len) {
		try {
			Lexer lexer = new Lexer(html);

			StringBuilder sb = new StringBuilder(html.length());
			Node node;
			while ((node = lexer.nextNode()) != null) {
				if ((node instanceof TextNode)) {
					sb.append(node.toHtml());
				}
				if (sb.length() > len) {
					break;
				}
			}
			return sb.toString();
		} catch (ParserException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean contains(String str, String search) {
		if ((StringUtils.isBlank(str)) || (StringUtils.isBlank(search))) {
			return false;
		}
		String reg = StringUtils.replace(search, "*", ".*");
		Pattern p = Pattern.compile(reg);
		return p.matcher(str).matches();
	}
	
	/**
	 * 解析并替换字符串中变量
	 * @param val
	 * @param values
	 * @return
	 */
	public static String replaceParams(String val, Map<String, String> values) {
		int stopDelim = val.indexOf(DELIM_STOP);
		int startDelim = val.indexOf(DELIM_START);
		while (stopDelim >= 0) {
			int idx = val.indexOf(DELIM_START, startDelim + DELIM_START.length());
			if ((idx < 0) || (idx > stopDelim)) {
				break;
			} else if (idx < stopDelim) {
				startDelim = idx;
			}
		}

		if ((startDelim < 0) && (stopDelim < 0)) {
			return val;
		} else if (((startDelim < 0) || (startDelim > stopDelim)) && (stopDelim >= 0)) {
			//throw new IllegalArgumentException("stop delimiter with no start delimiter: " + val);
			return val;
		}

		String variable = val.substring(startDelim + DELIM_START.length(), stopDelim);
		String substValue = values.get(variable);
		if(substValue == null){
			return val;
		}
		
		val = val.substring(0, startDelim) + substValue + val.substring(stopDelim + DELIM_STOP.length(), val.length());
		val = replaceParams(val, values);
		return val;
	}

}
