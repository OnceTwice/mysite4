package com.bit2016.mysite.vo;

public class GalleryVo {
	private Long no;
	private String oriFileName;
	private String saveFileName;
	private String comments;
	private String fileExtName;
	private Integer fileSize;
	private String regDate;
	private Long userNo;
	private String userName;

	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getOriFileName() {
		return oriFileName;
	}
	public void setOriFileName(String oriFileName) {
		this.oriFileName = oriFileName;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getFileExtName() {
		return fileExtName;
	}
	public void setFileExtName(String fileExtName) {
		this.fileExtName = fileExtName;
	}
	public Integer getFileSize() {
		return fileSize;
	}
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", oriFileName=" + oriFileName + ", saveFileName=" + saveFileName + ", comments="
				+ comments + ", fileExtName=" + fileExtName + ", fileSize=" + fileSize + ", regDate=" + regDate
				+ ", userNo=" + userNo + ", userName=" + userName + "]";
	}
}
