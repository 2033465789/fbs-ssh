package daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import base.BaseDao;
import beans.WebInfo;

@Repository
public class WebsiteDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<WebInfo> queryAsList() {
		Session session = getSession();
		List<WebInfo> list;
		try {
			Query<?> query = session.createQuery("from WebInfo");
			list = (List<WebInfo>) query.list();
		} finally {
			session.close();
		}
		return list;
	}

	public WebInfo deleteByName(String websiteName) {
		Session session = getSession();
		try {
			session.beginTransaction();
			WebInfo webinfo = null;
			Query<?> query = session
					.createQuery("from WebInfo where webName = :webName");
			query.setParameter("webName", websiteName);
			@SuppressWarnings("unchecked")
			List<WebInfo> list = (List<WebInfo>) query.list();
			if (list.size() == 1) {
				webinfo = session.load(WebInfo.class, list.get(0).getId());
				session.delete(webinfo);
			} else {
				return null;
			}
			session.getTransaction().commit();
			return webinfo;
		} finally {
			session.close();
		}
	}
}
