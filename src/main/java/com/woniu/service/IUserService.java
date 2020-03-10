package com.woniu.service;

import com.woniu.pojo.PageBean;
import com.woniu.pojo.User;

public interface IUserService {
	User findOne(Integer uid);

	PageBean<User> findByPage(PageBean<User> pageBean);

	int countAll(PageBean<User> pageBean);

	void update(User user);

	PageBean<User> businessByPage(PageBean<User> pageBean);
}
