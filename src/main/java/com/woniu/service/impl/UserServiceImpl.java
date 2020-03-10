package com.woniu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniu.dao.UserDao;
import com.woniu.pojo.PageBean;
import com.woniu.pojo.User;
import com.woniu.service.IUserService;
@Service
@Transactional
public class UserServiceImpl implements IUserService {
	@Autowired
	UserDao userDao;
	@Override
	public User findOne(Integer uid) {
		// TODO Auto-generated method stub
		return userDao.findOne(uid);
	}
	
	@Override
	public PageBean<User> findByPage(PageBean<User> pageBean) {
		// TODO Auto-generated method stub
		List<User> li = userDao.findByPage(pageBean);
		pageBean.setData(li);
		return pageBean;
	}

	@Override
	public int countAll(PageBean<User> pageBean) {
		// TODO Auto-generated method stub
		return userDao.countAll(pageBean);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public PageBean<User> businessByPage(PageBean<User> pageBean) {
		// TODO Auto-generated method stub
		List<User> li = userDao.businessByPage(pageBean);
		pageBean.setData(li);
		return pageBean;
	}

}
