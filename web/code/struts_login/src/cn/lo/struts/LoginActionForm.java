package cn.lo.struts;

import org.apache.struts.action.ActionForm;

public class LoginActionForm extends ActionForm {
	

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassWord(String password) {
		this.password = password;
	}
	
	
}
