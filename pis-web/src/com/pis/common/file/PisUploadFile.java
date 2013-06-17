/**
 * Copyright 2011-2012. Chongqing Crun Information & Network Co., Ltd. All rights reserved.
 * <a>http://www.crunii.com</a>
 */
package com.pis.common.file;

import java.io.File;
import java.io.Serializable;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Qiuxj create 2012-11-29
 *
 */
public class PisUploadFile implements Serializable{
	
	private static final long serialVersionUID = -1790519679363850512L;
	private String fileName;//文件名
	private String fileDesc;//文件描述
	private String realPath;//上传源文件物理路径
	private String relativePath;//上传源文件相对路径
	private File sourceFile;//源文件
	private String extension;//文件格式
	
	public PisUploadFile() {
	}
	
	public PisUploadFile(String relativePath) {
		this.relativePath = relativePath;
	}
	
	public PisUploadFile(String fileName, String relativePath) {
		this.fileName = fileName;
		this.relativePath = relativePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDesc() {
		return fileDesc;
	}

	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public File getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(396945557, 1291532951).appendSuper(
				super.hashCode()).append(this.extension).append(this.fileName)
				.append(this.relativePath).append(this.fileDesc).append(
						this.sourceFile).append(this.realPath).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("extension", this.extension).append("fileName",
						this.fileName)
				.append("relativePath", this.relativePath).append("fileDesc",
						this.fileDesc).append("realPath", this.realPath)
				.append("sourceFile", this.sourceFile).toString();
	}

}
