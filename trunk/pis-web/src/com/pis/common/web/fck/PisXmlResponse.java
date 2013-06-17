/**
 * Copyright 2011-2012. Chongqing Crun Information & Network Co., Ltd. All rights reserved.
 * <a>http://www.crunii.com</a>
 */
package com.pis.common.web.fck;

import java.io.File;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.fckeditor.handlers.CommandHandler;
import net.fckeditor.handlers.ResourceTypeHandler;
import net.fckeditor.tool.Utils;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author Qiuxj create 2012-12-27
 * 
 */
public class PisXmlResponse {

	private Document document;
	private Element errorElement;
	private Element foldersElement;
	private Element filesElement;
	public static final int EN_OK = 0;
	public static final int EN_ERROR = 1;
	public static final int EN_ALREADY_EXISTS = 101;
	public static final int EN_INVALID_FOLDER_NAME = 102;
	public static final int EN_SECURITY_ERROR = 103;
	public static final int EN_UKNOWN = 110;

	public PisXmlResponse(CommandHandler command,
			ResourceTypeHandler resourceType, String currentFolder,
			String constructedUrl) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();
			this.document = builder.newDocument();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}

		Element root = this.document.createElement("Connector");
		this.document.appendChild(root);
		root.setAttribute("command", command.toString());
		root.setAttribute("resourceType", resourceType.toString());

		Element currentFolderElement = this.document
				.createElement("CurrentFolder");
		currentFolderElement.setAttribute("path", currentFolder);

		currentFolderElement.setAttribute("url", constructedUrl);
		root.appendChild(currentFolderElement);
	}

	public PisXmlResponse(int number, String text) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();
			this.document = builder.newDocument();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}

		Element root = this.document.createElement("Connector");
		this.document.appendChild(root);
		setError(number, text);
	}

	public PisXmlResponse(int number) {
		this(number, null);
	}

	public void setError(int number, String text) {
		if (this.errorElement == null) {
			this.errorElement = this.document.createElement("Error");
			this.document.getDocumentElement().appendChild(this.errorElement);
		}

		this.errorElement.setAttribute("number", String.valueOf(number));
		if (Utils.isNotEmpty(text))
			this.errorElement.setAttribute("text", text);
	}

	public void setError(int number) {
		setError(number, null);
	}

	public void setFolders(File dir) {
		if (this.foldersElement != null) {
			Element parent = (Element) this.foldersElement.getParentNode();
			parent.removeChild(this.foldersElement);
		}

		this.foldersElement = this.document.createElement("Folders");
		this.document.getDocumentElement().appendChild(this.foldersElement);

		String[] fileList = dir.list(DirectoryFileFilter.DIRECTORY);
		String[] arr$ = fileList;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; i$++) {
			String file = arr$[i$];
			Element folderElement = this.document.createElement("Folder");
			folderElement.setAttribute("name", file);
			this.foldersElement.appendChild(folderElement);
		}
	}

	public void setFiles(File dir) {
		if (this.filesElement != null) {
			Element parent = (Element) this.filesElement.getParentNode();
			parent.removeChild(this.filesElement);
		}

		this.filesElement = this.document.createElement("Files");
		this.document.getDocumentElement().appendChild(this.filesElement);

		File[] fileList = dir.listFiles();

		File[] arr = fileList;
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			File file = arr[i];
			if(!file.isFile()){
				continue;
			}
			Element fileElement = this.document.createElement("File");
			long lastModified = file.lastModified();
			fileElement.setAttribute("name", file.getName());
			fileElement.setAttribute("lastModified", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(lastModified)));
			long length;
			if (file.length() < 1024L)
				length = 1L;
			else
				length = file.length() / 1024L;
			fileElement.setAttribute("size", String.valueOf(length));
			this.filesElement.appendChild(fileElement);
		}
	}

	public void setFoldersAndFiles(File dir) {
		setFolders(dir);
		setFiles(dir);
	}

	public String toString() {
		this.document.getDocumentElement().normalize();
		TransformerFactory factory = TransformerFactory.newInstance();

		StringWriter sw = new StringWriter();
		try {
			Transformer transformer = factory.newTransformer();

			DOMSource source = new DOMSource(this.document);
			StreamResult result = new StreamResult(sw);

			transformer.transform(source, result);
		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}

		return sw.toString();
	}
}
