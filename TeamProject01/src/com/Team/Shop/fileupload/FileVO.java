package com.Team.Shop.fileupload;

public class FileVO {

	private int idx;
	private String fileName;
	private String fileRealName;
	private int downloadCount;
	
	public FileVO() { }
	public FileVO(String fileName, String fileRealName, int downloadCount) {
		super();
		this.fileName = fileName;
		this.fileRealName = fileRealName;
		this.downloadCount = downloadCount;
	}
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileRealName() {
		return fileRealName;
	}
	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}
	public int getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}
	
	@Override
	public String toString() {
		return "FileVO [idx=" + idx + ", fileName=" + fileName + ", fileRealName=" + fileRealName + ", downloadCount="
				+ downloadCount + "]";
	}
	
}
