package com.woniu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniu.dao.MenuDao;
import com.woniu.pojo.Menu;
import com.woniu.pojo.PageBean;
import com.woniu.service.IMenuService;
@Service
@Transactional
public class MenuServiceImpl implements IMenuService {
	@Autowired
	MenuDao menuDao;




	@Override
	public int countCheck(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return menuDao.countCheck(map);
	}

	@Override
	public List<Menu> checkMenu(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Menu> list=menuDao.checkMenu(map);
		return list;
	}

}
