package actions;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;

import base.BaseAjaxAction;
import base.BaseDao;
import beans.WebInfo;
import services.WebsiteService;
import utils.App;
import utils.PropertiesUtil;
import utils.StaticDataUtil;

@Controller("adminAction")
@Scope("prototype")
public class AdminAction extends BaseAjaxAction
		implements
			ModelDriven<WebInfo> {
	private static final long serialVersionUID = 9038571423213056727L;

	/**
	 * 关闭数据库session级的连接，即无法获取hibernate的session
	 */

	// 文件上传
	private List<File> upload;
	private List<String> uploadContentType;
	private List<String> uploadFileName;

	public String closeConnection() {
		StaticDataUtil.connectStatus = false;
		try {
			responseString(SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String openConnection() {
		StaticDataUtil.connectStatus = true;
		try {
			responseString(SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * module for website
	 */

	WebInfo webInfo = new WebInfo();
	public String addWebsite() {
		if (getPermission() < StaticDataUtil.BASE_ADMIN) {
			return PERMISSION_DENY;
		}
		String callPath = PropertiesUtil.getWebImgPath()
				+ uploadFileName.get(0);
		webInfo.setImgURL(callPath);
		try {
			File savePath = new File(getRealPath(callPath));
			FileUtils.copyFile(upload.get(0), savePath);
			WebsiteService service = App.getBean(WebsiteService.class);
			service.save(webInfo);
			responseString(SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return null;
	}

	private String WebsiteName;

	public String deleteWebsite() {
		if (getPermission() < StaticDataUtil.BASE_ADMIN) {
			return PERMISSION_DENY;
		}
		WebsiteService service = App.getBean(WebsiteService.class);
		try {
			WebInfo webInfo = service.deleteByName(WebsiteName);
			if (webInfo != null) {
				// 删除网址图片
				String imgPath = webInfo.getImgURL();
				File file = new File(getRealPath(imgPath));
				FileUtils.forceDelete(file);
				responseString(SUCCESS);
			} else
				responseString(ERROR);
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
		return null;
	}

	private int connPermission;
	public String websiteBackground() {
		int permission = getPermission();
		if (permission < StaticDataUtil.BASE_ADMIN
				|| permission < BaseDao.getBaseConnectPermission()) {
			return PERMISSION_DENY;
		}
		connPermission = BaseDao.getBaseConnectPermission();
		return "websiteBackground";
	}

	@Override
	public WebInfo getModel() {
		return webInfo;
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getWebsiteName() {
		return WebsiteName;
	}

	public void setWebsiteName(String websiteName) {
		WebsiteName = websiteName;
	}

	public int getConnPermission() {
		return connPermission;
	}

	public void setConnPermission(int connPermission) {
		this.connPermission = connPermission;
	}

}
