package daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import base.BaseDao;
import beans.SharedResource;
import beans.User;
import exceptions.ViolationOperationException;
import utils.StaticDataUtil;

@Repository
public class SharedResourceDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<SharedResource> queryAsList() {
		Session session = getSession();
		List<SharedResource> list;
		try {
			Query<?> query = session.createQuery("from SharedResource");
			list = (List<SharedResource>) query.list();
		} finally {
			session.close();
		}
		return list;
	}

	public SharedResource queryComments(long fid) {
		Session session = getSession();
		SharedResource res = null;
		try {
			res = session.get(SharedResource.class, fid);
		} finally {
			session.close();
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	public List<SharedResource> queryBySearchInfo(String searchInfo) {
		List<SharedResource> list;
		Session session = getSession();
		try {
			Query<?> query = session.createQuery(
					"from SharedResource s where s.fileName like '%"
							+ searchInfo + "%' or s.fileDesc like '%"
							+ searchInfo + "%'");
			query.setMaxResults(StaticDataUtil.SHARED_RESOURCE_PAGE_SIZE);
			list = (List<SharedResource>) query.list();
		} finally {
			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SharedResource> queryByUser(User user) {
		List<SharedResource> list;
		Session session = getSession();
		try {
			Query<?> query = session.createQuery(
					"from SharedResource where uploadUser = :userName");
			query.setParameter("userName", user.getUserName());
			list = (List<SharedResource>) query.list();
		} finally {
			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public SharedResource deleteById(User user, String id)
			throws ViolationOperationException {
		Session session = getSession();
		try {
			Query<?> query = session
					.createQuery("from SharedResource where id=:id");
			query.setParameter("id", Long.parseLong(id));
			List<SharedResource> list = (List<SharedResource>) query.list();
			SharedResource item = null;
			if (list.size() == 0)
				return null;
			else if (list.size() == 1)
				item = list.get(0);
			session.close();
			if (item.getUploadUser().equals(user.getUserName()))
				delete(item);
			else
				throw new ViolationOperationException(
						"Trying to operate the data that not belong to you!");
			return list.get(0);
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SharedResource> getResourceByPage(long startIndex) {
		List<SharedResource> list;
		Session session = getSession();
		try {
			Query<?> query = session.createQuery("from SharedResource");
			query.setFirstResult((int) startIndex);
			query.setMaxResults(StaticDataUtil.SHARED_RESOURCE_PAGE_SIZE);
			list = (List<SharedResource>) query.list();
		} finally {
			session.close();
		}
		return list;
	}
}
