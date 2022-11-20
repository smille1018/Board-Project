package com.example.bean;

import java.util.Date;

public class BoardVO {
	private int sid;
	private String category;
	private String title;
	private String writer;
	private String photo;
	private String content;
	private Date regdate;
	private int cnt;
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getCategory() { return category; }
	public void setCategory(String category) { this.category = category; }
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPhoto() {return photo; }
	public void setPhoto(String photo) {this.photo = photo; }
	public String getContent() {return content; }
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
}
