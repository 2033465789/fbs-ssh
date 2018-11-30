package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beans.User;
import daos.UserDao;

@Service
public class UserService
{
	@Autowired
	private UserDao dao;

	public boolean existUser(User user)
	{
		return dao.query(user) != null;
	}

	public boolean existUser(String userName)
	{
		return dao.queryByName(userName) != null;
	}

	public long save(User user) throws Exception
	{
		return dao.save(user); 
	}
}
