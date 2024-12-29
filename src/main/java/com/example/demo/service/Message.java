package com.example.demo.service;

public class Message {
	
	private String to;
	private String title;
	private String text;
	
	public Message() {
		super();
	}

	public Message(String to, String title, String text) {
		super();
		this.to = to;
		this.title = title;
		this.text = text;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
