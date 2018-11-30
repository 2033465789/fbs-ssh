package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beans.WebInfo;
import daos.WebsiteDao;

@Service
public class WebsiteService {
	@Autowired
	private WebsiteDao dao;

	public long save(WebInfo item) throws Exception {
		return dao.save(item);
	}

	public List<WebInfo> getAllResource() {
		return dao.queryAsList();
	}

	public WebInfo deleteByName(String websiteName) {
		return dao.deleteByName(websiteName);
	}
}
