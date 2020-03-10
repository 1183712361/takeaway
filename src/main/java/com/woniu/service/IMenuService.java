package com.woniu.service;

import java.util.List;
import java.util.Map;

import com.woniu.pojo.Menu;


public interface IMenuService {

	int countCheck(Map<String, Object> map);

	List<Menu> checkMenu(Map<String, Object> map);

	

}
