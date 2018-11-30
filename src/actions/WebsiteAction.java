package actions;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import base.BaseAjaxAction;
import beans.WebInfo;
import services.WebsiteService;
import utils.App;

@Controller("websiteAction")
@Scope("prototype")
public class WebsiteAction extends BaseAjaxAction {
	private static final long serialVersionUID = -44587355358432668L;
	private List<WebInfo> webInfos;

	public String allWebsite() {
		WebsiteService service = App.getBean(WebsiteService.class);
		setWebInfos(service.getAllResource());
		return "allWebsite";
	}

	public String addWebsite() {
		if (getUser() == null || !getUser().hasBasePermission()) {
			return PERMISSION_DENY;
		}
		return "addWebsite";
	}

	public String deleteWebsite() {
		if (getUser() == null || !getUser().hasBasePermission()) {
			return PERMISSION_DENY;
		}
		return "deleteWebsite";
	}

	public List<WebInfo> getWebInfos() {
		return webInfos;
	}

	public void setWebInfos(List<WebInfo> webInfos) {
		this.webInfos = webInfos;
	}
}
