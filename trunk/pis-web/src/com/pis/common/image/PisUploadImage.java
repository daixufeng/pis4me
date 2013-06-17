/**
 * Copyright 2011-2012. Chongqing Crun Information & Network Co., Ltd. All rights reserved.
 * <a>http://www.crunii.com</a>
 */
package com.pis.common.image;

import java.io.Serializable;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Qiuxj create 2012-11-29
 *
 */
public class PisUploadImage implements Serializable{
	
	private static final long serialVersionUID = -1790519679363850512L;
	private String fileName;//文件名
	private String fileDesc;//文件描述
	private String relativePath;//上传源图片相对路径
	private String largeImage;//大图相对路径
	private String middleImage;//中图相对路径
	private String smallImage;//小图相对路径
	
	public PisUploadImage() {
	}
	
	public PisUploadImage(String relativePath) {
		this.relativePath = relativePath;
	}
	
	public PisUploadImage(String fileName, String relativePath) {
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

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public String getLargeImage() {
		return largeImage;
	}

	public void setLargeImage(String largeImage) {
		this.largeImage = largeImage;
	}

	public String getMiddleImage() {
		return middleImage;
	}

	public void setMiddleImage(String middleImage) {
		this.middleImage = middleImage;
	}

	public String getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-2026691777, -713132153).appendSuper(
				super.hashCode()).append(this.smallImage).append(this.fileName)
				.append(this.relativePath).append(this.fileDesc).append(
						this.middleImage).append(this.largeImage).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("smallImage", this.smallImage).append("largeImage",
						this.largeImage).append("fileName", this.fileName)
				.append("relativePath", this.relativePath).append(
						"middleImage", this.middleImage).append("fileDesc",
						this.fileDesc).toString();
	}

}
