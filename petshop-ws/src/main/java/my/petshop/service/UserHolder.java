package my.petshop.service;

import my.petshop.common.MyConstantes;

public class UserHolder {
	
	private String userLogin;
	private String userName;
	private String userPassword;
	private int profile;
	
	public UserHolder(){
		this.userLogin = "noLogin";
		this.userName="noName";
		this.userPassword = "noPassword";
		this.profile = MyConstantes.PROFILE_VISITOR;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getProfile() {
		return profile;
	}

	public void setProfile(int profile) {
		this.profile = profile;
	}

	public String toString() {
		return "User={"+userName+"/"+userLogin+"/"+userPassword+"/"+profile+"}";
	}

}
