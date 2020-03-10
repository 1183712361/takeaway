package com.woniu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniu.pojo.Menu;
import com.woniu.pojo.Message;
import com.woniu.pojo.PageBean;
import com.woniu.service.IMenuService;

@Controller
@RequestMapping("menu")
public class MenuController {
	@Autowired
	IMenuService menuService;
	@RequestMapping("index")
	public String index(Integer mtid,Integer uid,Model model){
		model.addAttribute("mtid", mtid);
		model.addAttribute("uid", uid);
		return "menu/index";
	}
	
	@ResponseBody
	@RequestMapping("checkMenu")
	public Object checkMenu(Integer mtid,Integer uid,PageBean<Menu> pageBean) {
		Message message = new Message();
		try {
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("mtid", mtid);
			map.put("uid", uid);
			if(pageBean.getCurrentPage()==null) {
				pageBean.setCurrentPage(1);
			}
			
			pageBean.setPageSize(3);
			
			map.put("pageBean", pageBean);
			
			int totalCount = menuService.countCheck(map);
			pageBean.setTotalCount(totalCount);
			int totalPage = pageBean.getTotalCount()%pageBean.getPageSize()==0?pageBean.getTotalCount()/pageBean.getPageSize():pageBean.getTotalCount()/pageBean.getPageSize()+1;
			pageBean.setPages(totalPage);
			List<Menu> list = menuService.checkMenu(map);
			pageBean.setData(list);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			message.setFlag(false);
		}
		message.setObj(pageBean);
		return message;
	}
}
