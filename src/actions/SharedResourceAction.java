package actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;

import base.BaseAjaxAction;
import beans.Comment;
import beans.SharedResource;
import services.SharedReourceService;
import utils.App;
import utils.PropertiesUtil;
import utils.StaticDataUtil;

@Controller("sharedResourceAction")
@Scope("prototype")
public class SharedResourceAction extends BaseAjaxAction
		implements
			ModelDriven<SharedResource> {
	private static final long serialVersionUID = 7068698159325749755L;
	/**
	 * 资源上传
	 */
	private SharedResource sharedResource = App.getBean(SharedResource.class);
	private List<File> upload;
	private List<String> uploadContentType;
	private List<String> uploadFileName;

	public String upload() {
		sharedResource.setUploadUser(getUser().getUserName());
		// userName + uplaodTime + fileNaeme

		String fileRealName = getUser().getUserName() + StaticDataUtil.SEPARATOR
				+ uploadFileName.get(0);

		// 文件原名
		sharedResource.setFileName(uploadFileName.get(0));

		sharedResource.setUploadTime(new Date(System.currentTimeMillis()));

		// 存储路径
		String savePath = PropertiesUtil.getSharedPath() + fileRealName;

		// 存储名
		sharedResource.setFilePath(fileRealName);
		SharedReourceService service = App.getBean(SharedReourceService.class);
		try {
			File save = new File(savePath);
			FileUtils.copyFile(upload.get(0), save);
			service.save(sharedResource);
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

	/**
	 * 资源下载
	 */
	private List<SharedResource> shared;
	private SharedResource file;
	private String searchInfo;
	private long startIndex = 0;
	private int page = 1;
	private long itemCount = 0L;

	public String download() {
		page--;
		SharedReourceService service = App.getBean(SharedReourceService.class);
		if (searchInfo == null) {
			startIndex = page * StaticDataUtil.SHARED_RESOURCE_PAGE_SIZE;
			shared = service.getResourceByPage(startIndex);
			itemCount = service.getItemCount(SharedResource.class);
		} else {
			shared = service.getResourceBySearch(searchInfo);
		}
		return "download";
	}

	/**
	 * 开启下载
	 */
	private String targetFile;
	private InputStream is = null;

	public String downloadFile() {
		String callPath = PropertiesUtil.getSharedPath() + targetFile;
		try {
			is = new FileInputStream(new File(callPath));
			if (is == null) {
				return ERROR;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "downloadFile";
	}

	public InputStream getInputStream() {
		return is;
	}

	public String getTargetFile() {
		try {
			targetFile = URLEncoder.encode(targetFile, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int beginIndex = targetFile.indexOf(StaticDataUtil.SEPARATOR)
				+ StaticDataUtil.SEPARATOR.length();
		return targetFile.substring(beginIndex);
	}

	public void setTargetFile(String targetFile) {
		this.targetFile = targetFile;
	}

	/**
	 * 返回文件的具体信息
	 */
	private String fid;
	private String content;

	public String showDetails() {
		SharedReourceService service = App.getBean(SharedReourceService.class);
		file = service.getAllComments(Long.parseLong(fid));
		return "showDetails";
	}

	public String makeComment() {
		if (getUser() == null) {
			return "offline";
		}
		SharedReourceService service = App.getBean(SharedReourceService.class);
		Calendar afterOneMonth = Calendar.getInstance();
		afterOneMonth.add(Calendar.MONTH, 1);
		Comment comment = new Comment(getUser().getUserName(), fid, content,
				Calendar.getInstance().getTime(), afterOneMonth.getTime(), 0L);
		try {
			service.makeComment(comment);
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public List<SharedResource> getShared() {
		return shared;
	}

	public void setShared(LinkedList<SharedResource> shared) {
		this.shared = shared;
	}

	public String getSearchInfo() {
		return searchInfo;
	}

	public void setSearchInfo(String searchInfo) {
		this.searchInfo = searchInfo;
	}

	@Override
	public SharedResource getModel() {
		return sharedResource;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public SharedResource getFile() {
		return file;
	}

	public void setFile(SharedResource file) {
		this.file = file;
	}

	public long getPageSize() {
		return StaticDataUtil.SHARED_RESOURCE_PAGE_SIZE;
	}

	public long getItemCount() {
		return itemCount;
	}

	public void setItemCount(long itemCount) {
		this.itemCount = itemCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
