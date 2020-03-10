package com.woniu.service;

import com.woniu.pojo.MenuType;
import com.woniu.pojo.PageBean;

public interface IMenuTypeService {

	PageBean<MenuType> findByPage(PageBean<MenuType> pageBean);

	int countAll(PageBean<MenuType> pageBean);

}
