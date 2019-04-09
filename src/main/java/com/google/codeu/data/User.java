package com.google.codeu.data;
import java.util.HashSet;
public class User {
	private String email;
	private String aboutMe;
	private String name;
	private String city;
	private HashSet<String> interests;
	

	
	public User(String email, String aboutMe) {
		this.email = email;
		this.aboutMe = aboutMe;
		this.name = "";
		this.city = "";
		this.interests = new HashSet<String>();
	}

	public User(String email, String name, String city, String interests) {
		this.email = email;
		this.name = name;
		this.city = city;
		this.interests = new HashSet<String>();
		
		String s[] = interests.split(",");
		
		for(String a: s){
			System.out.println(a);
			this.interests.add(a);
		}
	}

	public User(String email, String name, String city, HashSet<String> interests) {
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

	public HashSet<String> getInterests(){
		return interests;
	}
	public String getInterestsString(){
		String ans = "";
		for(String s: interests){
			ans += (s + ",");

		}
		ans = ans.substring(0, ans.length()-1);
		return ans;
	}
	public void setInterests(String interests){
		String[] s = interests.split(",");
		for(String a: s){
			this.interests.add(a);
		}
	}
}
