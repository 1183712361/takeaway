package com.woniu.dao;

import java.util.List;

import com.woniu.pojo.PageBean;
import com.woniu.pojo.User;

public interface UserDao {
	User findOne(Integer uid);

	List<User> findByPage(PageBean<User> pageBean);

	int countAll(PageBean<User> pageBean);

	void update(User user);

	List<User> businessByPage(PageBean<User> pageBean);
}
