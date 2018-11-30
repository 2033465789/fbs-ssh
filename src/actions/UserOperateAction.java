package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import base.BaseAjaxAction;
import beans.User;
import services.UserService;
import utils.App;

@Controller("userOperateAction")
@Scope("prototype")
public class UserOperateAction extends BaseAjaxAction {
	private static final long serialVersionUID = 1413147347521568403L;

	private String userName;
	private String password;

	// 登录
	public String login() throws IOException, ServletException {
		UserService userService = App.getBean(UserService.class);
		User user = new User(userName, password);
		if (userService.existUser(user)) {
			addSessionAttribute("user", user);
			responseString(SUCCESS);
		} else {
			responseString(ERROR);
		}
		return null;
	}

	// 注册
	public String signup() throws IOException, ServletException {
		UserService userService = App.getBean(UserService.class);
		User user = new User(userName, password);
		if (userService.existUser(user.getUserName())) {
			responseString(getText("userNameHasExist"));
		} else {
			try {
				userService.save(user);
			} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			}
			addSessionAttribute("user", user);
			responseString(SUCCESS);
		}
		return null;
	}

	// 注销
	public String logout() {
		User user = getUser();
		if (user == null) {
			return null;
		}
		getSession().removeAttribute("user");
		if (getSession().getAttribute("user") == null) {
			try {
				responseString(SUCCESS);
			} catch (IOException e) {
				e.printStackTrace();
				return ERROR;
			}
		}
		return null;
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

}
