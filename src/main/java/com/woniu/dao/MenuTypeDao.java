package com.woniu.dao;

import java.util.List;

import com.woniu.pojo.MenuType;
import com.woniu.pojo.PageBean;

public interface MenuTypeDao {

	List<MenuType> findByPage(PageBean<MenuType> pageBean);

	int countAll(PageBean<MenuType> pageBean);

	

}
