package com.google.codeu.data;

public class User {
	private String email;
	private String aboutMe;
	private String name;
	private String city;
	private String interests;

	
	public User(String email, String aboutMe) {
		this.email = email;
		this.aboutMe = aboutMe;
		this.name = "";
		this.city = "";
		this.interests = "";
	}

	public User(String email, String name, String city, String interests) {
		this.email = email;
		this.name = name;
		this.city = city;
		this.interests = interests;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getAboutMe() {
		return aboutMe;
	}
	public String getName(){
		return name;
	}
	public  void setName(String name){
		this.name = name;
	}
	public String getCity(){
		return city;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getInterests(){
		return interests;
	}
	public void setInterests(String interests){
		this.interests = interests;
	}
}
