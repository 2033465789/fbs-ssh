package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beans.Comment;
import beans.SharedResource;
import beans.User;
import daos.SharedResourceDao;
import exceptions.ViolationOperationException;

@Service
public class SharedReourceService {
	@Autowired
	private SharedResourceDao dao;

	public long save(SharedResource item) {
		return dao.save(item);
	}

	public List<SharedResource> getAllResource() {
		return dao.queryAsList();
	}

	public SharedResource getAllComments(long fid) {
		return dao.queryComments(fid);
	}

	public long makeComment(Comment comment) {
		return dao.save(comment);
	}

	public List<SharedResource> getResourceBySearch(String searchInfo) {
		return dao.queryBySearchInfo(searchInfo);
	}

	public List<SharedResource> userAllShared(User user) {
		return dao.queryByUser(user);
	}

	public SharedResource delete(User user, String id) throws ViolationOperationException {
		return dao.deleteById(user, id);
	}

	public List<SharedResource> getResourceByPage(long startIndex) {
		return dao.getResourceByPage(startIndex);
	}

	public long getItemCount(Class<?> cls) {
		return dao.getItemCount(cls.getSimpleName());
	}
}
