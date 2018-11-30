package beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import utils.StaticDataUtil;

@Component
@Scope("prototype")
public class User {
	private long uid;
	private String userName;
	private String password;
	private int permission;

	public User() {
		permission = 1;
	}

	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
		permission = 1;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public boolean hasSuperPermission() {
		return getPermission() == StaticDataUtil.SUPER_ADMIN;
	}

	public boolean hasBasePermission() {
		return getPermission() >= StaticDataUtil.BASE_ADMIN;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", userName=" + userName + ", password=" + password + ", permission=" + permission
				+ "]";
	}
}
