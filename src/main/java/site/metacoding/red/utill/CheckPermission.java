package site.metacoding.red.utill;

import javax.servlet.http.HttpSession;

import site.metacoding.red.domain.users.Users;

public class CheckPermission {
	
	static Users users;
	static Integer usersid;
	
	public static Users CheckingPermission(HttpSession session) {
		users = (Users)session.getAttribute("principal");
		return users;
	}
	
	public Users getUserInfo(HttpSession session) {
		return users;
	}
	
	public static Integer getUsersid() {
		
		try {
			usersid = users.getId();
		} catch (Exception e) {
			usersid = null;
		}
		return usersid;
	}

}
