package com.pis.xslt;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;

import org.springframework.web.servlet.view.xslt.AbstractXsltView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

@SuppressWarnings("deprecation")
public class WordPage extends AbstractXsltView {
	protected Source createXsltSource(Map<String, Object> model, String rootName,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Document document = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().newDocument();
		Element root = document.createElement(rootName);
		@SuppressWarnings("unchecked")
		List<String> words = (List<String>) model.get("wordList");
		for (Iterator<String> it = words.iterator(); it.hasNext();) {
			String nextWord = (String) it.next();
			Element wordNode = document.createElement("word");
			Text textNode = document.createTextNode(nextWord);
			wordNode.appendChild(textNode);
			root.appendChild(wordNode);
		}
		return new DOMSource(root);
	}
}
