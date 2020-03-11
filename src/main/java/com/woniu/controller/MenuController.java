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
import com.woniu.pojo.User;
import com.woniu.service.IMenuService;
import com.woniu.service.IUserService;

@Controller
@RequestMapping("menu")
public class MenuController {
	@Autowired
	IMenuService menuService;
	@Autowired
	IUserService userService;
	/**
	 * 查询了一下商家信息因为要在顶层展示
	 * @param mtid
	 * @param uid
	 * @param model
	 * @return
	 */
	@RequestMapping("index")
	public String index(Integer mtid,Integer bid,Model model){
		User user = userService.findOne(bid);
		model.addAttribute("mtid", mtid);
		model.addAttribute("bid", bid);
		model.addAttribute("user", user);
		return "menu/index";
	}
	
	
	/**
	 * 对应商家和菜单的菜品展示
	 * @param mtid
	 * @param uid
	 * @param pageBean
	 * @return
	 */
	@ResponseBody
	@RequestMapping("checkMenu")
	public Object checkMenu(Integer mtid,Integer bid,PageBean<Menu> pageBean) {
		Message message = new Message();
		try {
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("mtid", mtid);
			map.put("bid", bid);
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
