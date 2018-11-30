package actions;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;

import base.BaseAjaxAction;
import beans.Good;
import beans.User;
import services.LostFoundService;
import utils.App;
import utils.InputCheckUtil;
import utils.PropertiesUtil;
import utils.StaticDataUtil;
import utils.StringUtil;

@Controller("lostFoundAction")
@Scope("prototype")
public class LostFoundAction extends BaseAjaxAction
		implements
			ModelDriven<Good> {
	private static final long serialVersionUID = 3491892326002112100L;

	private Good good = App.getBean(Good.class);
	private File image;
	private String imageContentType;
	private String imageFileName;
	private String searchInfo;
	private long startId = 0;
	private final long pageSize = 10;

	// the list that return to the cilent
	private List<Good> list;
	@Autowired
	private LostFoundService service;

	// submit the find good
	public String find() {
		User user = getUser();
		if (user == null)
			return ERROR;
		try {
			if (!InputCheckUtil.isOK(good.toString())) {
				responseString("...what do you want to do???");
			}
			good.setFinderId(user.getUserName());
			String callPath = PropertiesUtil.getImagesPath()
					+ getUser().getUserName() + StaticDataUtil.SEPARATOR
					+ StringUtil.getTimeByFormatter("yyyyMMddHHmmssSSS")
					+ StaticDataUtil.SEPARATOR + imageFileName;
			String savePath = ServletActionContext.getServletContext()
					.getRealPath(callPath);
			// save text data to database
			good.setImagePath(callPath);
			service.save(good);
			FileUtils.copyFile(image, new File(savePath));
			responseString(SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
		return null;
	}

	// return the recently lost goods
	public String recentlyGoods() {
		list = service.queryAsList();
		if (list.isEmpty()) {
			try {
				responseString("暂无资源");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		try {
			StringBuilder res = new StringBuilder(
					"<table id='recently-good-table'>");
			res.append(
					"<thead><tr> <td>失主姓名</td> <td>物品描述</td> <td>物品图片</td></tr></thead>  <tbody>");
			for (Good item : list) {
				res.append("<tr>");
				res.append("<td>").append(item.getLosterName()).append("</td>");
				res.append("<td>").append(item.getGoodDesc()).append("</td>");
				res.append("<td><img img data-recently='data-recently' src='")
						.append(item.getImagePath()).append("'</td>");
				res.append("</tr> ");
			}
			res.append("</table>");
			responseString(res.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String allGoods() {
		List<Good> list = service.queryAsPage(startId, pageSize);
		StringBuilder res = new StringBuilder("<table id='lost-good-table'>");
		res.append(
				"<thead><tr> <td>失主姓名</td> <td>数字信息</td> <td>物品描述</td> <td>拾取地点</td> "
						+ "<td>联系人姓名</td> <td>联系人电话</td> <td>QQ或者微信</td> <td>物品图片</td></tr></thead>  <tbody>");
		for (Good item : list) {
			makeTr(res, item);
		}
		res.append("</table>");
		try {
			responseString(res.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String search() {
		List<Good> list = service.queryBySearchInfo(searchInfo);
		StringBuilder res = new StringBuilder("<table id='lost-good-table'>");
		res.append(
				"<thead><tr> <td>失主姓名</td> <td>数字信息</td> <td>物品描述</td> <td>拾取地点</td> "
						+ "<td>联系人姓名</td> <td>联系人电话</td> <td>QQ或者微信</td> <td>物品图片</td></tr></thead>  <tbody>");
		for (Good item : list)
			makeTr(res, item);
		res.append("</table>");
		try {
			responseString(res.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void makeTr(StringBuilder res, Good good) {
		res.append("<tr>");
		res.append("<td>").append(good.getLosterName()).append("</td>");
		res.append("<td>").append(good.getNumberInfo()).append("</td>");
		res.append("<td>").append(good.getGoodDesc()).append("</td>");
		res.append("<td>").append(good.getFoundAddr()).append("</td>");
		res.append("<td>").append(good.getFinderName()).append("</td>");
		res.append("<td>").append(good.getFinderPhone()).append("</td>");
		res.append("<td>").append(good.getFinderQQorWX()).append("</td>");
		res.append("<td><img src='").append(good.getImagePath())
				.append("'></td>");
		res.append("</tr> ");
	}

	@Override
	public Good getModel() {
		return good;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File imageFile) {
		this.image = imageFile;
	}

	public List<Good> getList() {
		return list;
	}

	public void setList(List<Good> list) {
		this.list = list;
	}

	public long getStartId() {
		return startId;
	}

	public void setStartId(long startId) {
		this.startId = startId;
	}

	public String getSearchInfo() {
		return searchInfo;
	}

	public void setSearchInfo(String searchInfo) {
		this.searchInfo = searchInfo;
	}

}
