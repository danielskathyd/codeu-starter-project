package com.google.codeu.data;

public class User {
	private String email;
	private String aboutMe;
	private int messageCount;

	public User(String email, String aboutMe ) {
		this.email = email;
		this.aboutMe = aboutMe;
		this.messageCount = 0;
	}

	public String getEmail() {
		return email;
	}
	public String getAboutMe() {
		return aboutMe;
	}
	public void addMessage() {
		this.messageCount = this.messageCount + 1;
	}
}
