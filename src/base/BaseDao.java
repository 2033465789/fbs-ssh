package base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao {
	@Autowired
	private SessionFactory sessionFactory;
	private static int baseConnectPermission = 1;
	// 插入条目
	public long save(Object item) {
		long id = -1;
		Session session = null;
		try {
			session = getSession();
			// 开启事务
			session.beginTransaction();
			id = (long) session.save(item);
			// 提交事务
			session.getTransaction().commit();
		} finally {
			if (session != null)
				session.close();
		}
		return id;
	}

	public void update(Object item) {
		Session session = null;
		try {
			session = getSession();
			// 开启事务
			session.beginTransaction();
			session.update(item);
			// 提交事务
			session.getTransaction().commit();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public void delete(Object item) {
		Session session = null;
		try {
			session = getSession();
			// 开启事务
			session.beginTransaction();
			session.delete(item);
			// 提交事务
			session.getTransaction().commit();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public Session getSession() {
		return sessionFactory.openSession();
	}

	public long getItemCount(String className) {
		Session session = sessionFactory.openSession();
		long res = 0;
		try {
			Query<?> query = session
					.createQuery("select count(*) from " + className);
			res = (Long) query.uniqueResult();
		} finally {
			session.close();
		}
		return res;
	}

	public static int getBaseConnectPermission() {
		return baseConnectPermission;
	}

	public static void setBaseConnectPermission(int connectPermission) {
		baseConnectPermission = connectPermission;
	}
}
