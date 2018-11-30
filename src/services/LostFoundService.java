package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beans.Good;
import beans.User;
import daos.LostFoundDao;
import exceptions.ViolationOperationException;

@Service
public class LostFoundService {
	@Autowired
	public LostFoundDao dao;

	public long save(Good good) {
		return dao.save(good);
	}

	public List<Good> queryAsList() {
		return dao.queryAsList();
	}

	public List<Good> userAllFind(User user) {
		return dao.queryByUser(user);
	}

	public List<Good> queryAsPage(long startId, long pageSize) {
		return dao.queryAsPage(startId, pageSize);
	}

	public List<Good> queryBySearchInfo(String searchInfo) {
		return dao.queryBySearchInfo(searchInfo);
	}

	public Good delete(User user, String id) throws ViolationOperationException {
		return dao.deleteById(user, id);
	}
}
