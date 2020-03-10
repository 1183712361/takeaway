package com.woniu.dao;

import java.util.List;
import java.util.Map;

import com.woniu.pojo.Menu;


public interface MenuDao {

	int countCheck(Map<String, Object> map);

	List<Menu> checkMenu(Map<String, Object> map);

}
