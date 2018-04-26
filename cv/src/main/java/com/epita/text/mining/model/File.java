package com.epita.text.mining.model;

public class File {
	private static final long serialVersionUID = 1L;

	private String fileName;
	private String fileData;
	private String type; // This is to identify the file that is being downloaded
	private String path;

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileData
	 */
	public String getFileData() {
		return fileData;
	}

	/**
	 * @param fileData
	 *            the fileData to set
	 */
	public void setFileData(String fileData) {
		this.fileData = fileData;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "File [fileName=" + fileName + ", fileData=" + fileData + ", type=" + type + ", path=" + path + "]";
	}

}
