package daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import base.BaseDao;
import beans.Good;
import beans.User;
import exceptions.ViolationOperationException;

@Repository
public class LostFoundDao extends BaseDao {

	public Good queryById(long gid) {
		Session session = null;
		try {
			session = getSession();
			Query<?> query = session.createQuery("from Good where gid = :gid");
			query.setParameter("gid", gid);
			@SuppressWarnings("unchecked")
			List<Good> list = (List<Good>) query.list();
			if (list.size() > 0)
				return list.get(0);
			else
				return null;
		} finally {
			if (session != null)
				session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Good> queryByUser(User user) {
		Session session = getSession();
		List<Good> list = null;
		try {
			Query<?> query = session
					.createQuery("from Good where finderId = :userName");
			query.setParameter("userName", user.getUserName());
			list = (List<Good>) query.list();
		} finally {
			session.close();
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Good> queryAsPage(long startId, long pageSize) {
		Session session = getSession();
		List<Good> list = null;
		try {
			Query<?> query = session
					.createQuery("from Good where gid >= :startId");
			query.setParameter("startId", startId);
			query.setMaxResults((int) pageSize);
			list = (List<Good>) query.list();
		} finally {
			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Good> queryAsList() {
		Session session = getSession();
		List<Good> list;
		try {
			Query<?> query = session.createQuery("from Good order by gid desc");
			query.setMaxResults(3);
			list = (List<Good>) query.list();
		} finally {
			session.close();
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Good> queryBySearchInfo(String searchInfo) {
		Session session = getSession();
		List<Good> list = null;
		try {
			Query<?> query = session.createQuery(
					"from Good g where ( g.numberInfo like '%" + searchInfo
							+ "%' or g.losterName like '%" + searchInfo
							+ "%'  or g.goodDesc like '%" + searchInfo
							+ "%' or g.foundAddr like '%" + searchInfo + "%')");
			list = (List<Good>) query.list();
		} finally {
			session.close();
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	public Good deleteById(User user, String id)
			throws ViolationOperationException {
		Session session = getSession();
		try {
			Query<?> query = session.createQuery("from Good where gid=:gid");
			query.setParameter("gid", Long.parseLong(id));
			List<Good> list = (List<Good>) query.list();
			Good item = null;
			if (list.size() == 0)
				return null;
			else if (list.size() == 1)
				item = list.get(0);
			session.close();
			if (item.getFinderId().equals(user.getUserName()))
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
}
