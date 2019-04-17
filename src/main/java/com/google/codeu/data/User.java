package com.google.codeu.data;
import java.util.HashSet;
public class User {
	private String email;
	private String aboutMe;
	private String name;
	private String city;
	private HashSet<String> interests;
	private String lat;
	private String lon;
	

	
	public User(String email, String aboutMe) {
		this.email = email;
		this.aboutMe = aboutMe;
		this.name = "";
		this.city = "";
		this.interests = new HashSet<String>();
		this.lon = "";
		this.lat = "";
	}

	public User(String email, String name, String city, String interests,String lon, String lat) {
		this.email = email;
		this.name = name;
		this.city = city;
		this.interests = new HashSet<String>();
		this.lon = lon;
		this.lat = lat;
		String s[] = interests.split(",");
		
		for(String a: s){
			a = a.strip();
			System.out.println(a);
			this.interests.add(a);
		}
	}

	public User(String email, String name, String city, HashSet<String> interests, String lon, String lat) {
		this.email = email;
		this.name = name;
		this.city = city;
		this.interests = interests;
		this.lon = lon;
		this.lat = lat;
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
	public String getLon(){return lon;}
	public String getLat(){return lat;}
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
			a = a.strip();
			this.interests.add(a);
		}
	}
	public void setLat(String lat){this.lat = lat;}
	public void setLon(String lon){this.lon = lon;}
}
