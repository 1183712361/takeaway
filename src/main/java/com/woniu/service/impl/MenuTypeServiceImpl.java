package com.woniu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniu.dao.MenuTypeDao;
import com.woniu.pojo.MenuType;
import com.woniu.pojo.PageBean;
import com.woniu.service.IMenuTypeService;
@Service
@Transactional
public class MenuTypeServiceImpl implements IMenuTypeService {
	@Autowired
	MenuTypeDao menuTypeDao;
	
	@Override
	public PageBean<MenuType> findByPage(PageBean<MenuType> pageBean) {
		// TODO Auto-generated method stub
		List<MenuType> list=menuTypeDao.findByPage(pageBean);
		pageBean.setData(list);
		return pageBean;
	}

	
	@Override
	public int countAll(PageBean<MenuType> pageBean) {
		// TODO Auto-generated method stub
		return menuTypeDao.countAll(pageBean);
	}

}
