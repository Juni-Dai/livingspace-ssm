package cn.juni.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.juni.dao.IUser;
import cn.juni.pojo.User;
import cn.juni.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	IUser iuser;
	
	@Override
	public User login(User user) {
		return iuser.login(user);
	}

}
