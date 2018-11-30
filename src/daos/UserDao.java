package daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import base.BaseDao;
import beans.User;

@Repository
public class UserDao extends BaseDao {

	public User queryById(int uid) {
		Session session = getSession();
		try {
			Query<?> query = session.createQuery("from User where uid = :uid");
			query.setParameter("uid", uid);
			@SuppressWarnings("unchecked")
			List<User> list = (List<User>) query.list();
			if (list.size() > 0)
				return list.get(0);
			else
				return null;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public User queryByName(String userName) {
		Session session = getSession();
		List<User> list = null;
		try {
			Query<?> query = session
					.createQuery("from User where userName = :userName");
			query.setParameter("userName", userName);
			list = (List<User>) query.list();
			if (list != null && list.size() > 0)
				return list.get(0);
			else
				return null;
		} finally {
			session.close();
		}
	}

	public User query(User user) {
		Session session = getSession();
		try {
			Query<?> query = session.createQuery(
					"from User where userName = :userName and password = :password");
			query.setParameter("userName", user.getUserName());
			query.setParameter("password", user.getPassword());
			@SuppressWarnings("unchecked")
			List<User> list = (List<User>) query.list();

			if (list != null && list.size() > 0) {
				// 处理用户数据
				User tmpUser = list.get(0);
				user.setPermission(tmpUser.getPermission());
				user.setPassword(null);
				return user;
			} else
				return null;
		} finally {
			session.close();
		}
	}
}
