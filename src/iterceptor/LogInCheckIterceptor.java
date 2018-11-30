package iterceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import beans.User;

public class LogInCheckIterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -4745540448403035500L;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession(false)
				.getAttribute("user");
		if (user == null) {
			return "login";
		}
		return arg0.invoke();
	}
}
