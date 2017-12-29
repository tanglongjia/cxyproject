package com.cxy.web.po;


import com.cxy.frame.orm.BaseEntity;

public class BsFile extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2107330347794851962L;

	
	private String fileName;
	
	private String filePath;
	
	private String fileType;
	
	private String viewFilePath;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getViewFilePath() {
		return viewFilePath;
	}

	public void setViewFilePath(String viewFilePath) {
		this.viewFilePath = viewFilePath;
	}
	
	
}
