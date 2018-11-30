package base;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

import beans.User;

/**
 * the base action that need ajax.
 * 
 * @author blue
 */
public class BaseAjaxAction extends ActionSupport {
	private static final long serialVersionUID = -5851969453494095897L;

	public static final String PERMISSION_DENY = "permission_deny";
	public static final String OFFLINE = "offline";
	private HttpServletResponse response;
	private HttpServletRequest request;
	private HttpSession session;

	@PostConstruct
	public void init() {
		ValueStack stack = ActionContext.getContext().getValueStack();
		stack.set("contextPath", ServletActionContext.getServletContext().getContextPath());
	}

	// return string to the cilent.
	public void responseString(String resStr) throws IOException {
		getResponse().getWriter().append(resStr).flush();
	}

	public User getUser() {
		return (User) getSession().getAttribute("user");
	}

	// add attribute to the session
	public void addSessionAttribute(String key, Object obj) {
		getSession().setAttribute(key, obj);
	}

	public HttpServletResponse getResponse() {
		if (response == null) {
			response = ServletActionContext.getResponse();
			response.setCharacterEncoding(getText("characterEncoding", "utf-8"));
		}
		return response;
	}

	public HttpServletRequest getRequest() {
		if (response == null) {
			request = ServletActionContext.getRequest();
			try {
				request.setCharacterEncoding(getText("characterEncoding", "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return request;
	}

	public HttpSession getSession() {
		if (session == null) {
			session = getRequest().getSession();
		}
		return session;
	}

	public String getRealPath(String para) {
		return ServletActionContext.getServletContext().getRealPath(para);
	}

	public int getPermission() {
		User user = getUser();
		return user == null ? -1 : user.getPermission();
	}
}
