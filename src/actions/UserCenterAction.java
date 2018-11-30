package actions;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import base.BaseAjaxAction;
import beans.Good;
import beans.SharedResource;
import beans.User;
import exceptions.ViolationOperationException;
import services.LostFoundService;
import services.SharedReourceService;
import utils.App;
import utils.PropertiesUtil;

@Controller("userCenterAction")
@Scope("prototype")
public class UserCenterAction extends BaseAjaxAction {
	private static final long serialVersionUID = -4403123061210447793L;

	private List<Good> allFind;
	private String id;

	public String allFind() {
		User user = getUser();
		LostFoundService service = App.getBean(LostFoundService.class);
		setAllFind(service.userAllFind(user));
		return "allFind";
	}

	private List<SharedResource> allShared;

	public String allShared() {
		User user = getUser();
		SharedReourceService service = App.getBean(SharedReourceService.class);
		allShared = service.userAllShared(user);
		return "allShared";
	}
	public String findLoster() throws IOException {
		User user = getUser();
		LostFoundService service = App.getBean(LostFoundService.class);
		// 删除数据库数据
		try {
			Good item = service.delete(user, id);
			if (item == null)
				return ERROR;
			// 删除磁盘文件
			File delFile = new File(getRealPath(item.getImagePath()));
			if (delFile.exists())
				FileUtils.forceDelete(delFile);
			responseString(SUCCESS);
		} catch (ViolationOperationException e) {
			e.printStackTrace();
			System.err.println(user.getUserName() + "did a violation operate!");
			responseString(e.getErrorInfo());
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
		return null;
	}
	public String deleteFind() throws IOException {
		User user = getUser();
		LostFoundService service = App.getBean(LostFoundService.class);
		// 删除数据库数据
		try {
			Good item = service.delete(user, id);
			if (item == null)
				return ERROR;
			// 删除磁盘文件
			File delFile = new File(getRealPath(item.getImagePath()));
			if (delFile.exists())
				FileUtils.forceDelete(delFile);
			responseString(SUCCESS);
		} catch (ViolationOperationException e) {
			e.printStackTrace();
			System.err.println(user.getUserName() + "did a violation operate!");
			responseString(e.getErrorInfo());
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
		return null;
	}

	public String deleteShared() throws IOException {
		User user = getUser();
		SharedReourceService service = App.getBean(SharedReourceService.class);
		try {
			// 删除数据库数据
			SharedResource item = service.delete(user, id);
			if (item == null)
				return ERROR;
			// 删除磁盘文件
			File delFile = new File(
					PropertiesUtil.getSharedPath() + item.getFilePath());
			if (delFile.exists())
				FileUtils.forceDelete(delFile);
			responseString(SUCCESS);
		} catch (ViolationOperationException e) {
			e.printStackTrace();
			System.err
					.println(user.getUserName() + " did a violation operate!");
			responseString(e.getErrorInfo());
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Good> getAllFind() {
		return allFind;
	}

	public void setAllFind(List<Good> allFind) {
		this.allFind = allFind;
	}

	public List<SharedResource> getAllShared() {
		return allShared;
	}

	public void setAllShared(List<SharedResource> allShared) {
		this.allShared = allShared;
	}

}
