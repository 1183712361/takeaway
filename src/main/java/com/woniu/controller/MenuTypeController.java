package com.woniu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniu.pojo.MenuType;
import com.woniu.pojo.Message;
import com.woniu.pojo.PageBean;
import com.woniu.service.IMenuTypeService;

@Controller
@RequestMapping("menutype")
public class MenuTypeController {
	
	@Autowired
	IMenuTypeService menutypeService;
	@RequestMapping("index")
	public String index() {
		return "menutype/index";
	}
	
	@RequestMapping("menutypeByPage")
	@ResponseBody
	public Object menutypeByPage(PageBean<MenuType> pageBean) {
		Message message = new Message();
		try {
			if(pageBean.getCurrentPage()==null) {
				pageBean.setCurrentPage(1);
			}
			pageBean.setPageSize(3);
			int totalCount = menutypeService.countAll(pageBean);
			pageBean.setTotalCount(totalCount);
			int totalPage = pageBean.getTotalCount()%pageBean.getPageSize()==0?pageBean.getTotalCount()/pageBean.getPageSize():pageBean.getTotalCount()/pageBean.getPageSize()+1;
			pageBean.setPages(totalPage);
			pageBean=menutypeService.findByPage(pageBean);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			message.setFlag(false);
		}
		message.setObj(pageBean);
		return message;
	}
}
