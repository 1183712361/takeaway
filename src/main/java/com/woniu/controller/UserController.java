package com.woniu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniu.pojo.Message;
import com.woniu.pojo.PageBean;
import com.woniu.pojo.Role;
import com.woniu.pojo.User;
import com.woniu.service.IUserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	IUserService userService;
	@RequestMapping("index")
	public String index(HttpSession session) {
		User loginUser = new User();
		Role role = new Role();
		role.setRid(1);
		loginUser.setRole(role);
		loginUser.setUname("Jay");
		loginUser.setUid(1);
		session.setAttribute("loginUser", loginUser);
		return "user/index";
	}
	
	@RequestMapping("userInfo")
	public String userInfo(Integer uid,Model model) {
		User user = userService.findOne(uid);
		model.addAttribute("user", user);
		return "user/userInfo";
	}
	
	@RequestMapping("userByPage")
	@ResponseBody
	public Object userByPage(PageBean<User> pageBean) {
		Message message = new Message();
		try {
			if(pageBean.getCurrentPage()==null) {
				pageBean.setCurrentPage(1);
			}
			pageBean.setPageSize(3);
			int totalCount = userService.countAll(pageBean);
			pageBean.setTotalCount(totalCount);
			int totalPage = pageBean.getTotalCount()%pageBean.getPageSize()==0?pageBean.getTotalCount()/pageBean.getPageSize():pageBean.getTotalCount()/pageBean.getPageSize()+1;
			pageBean.setPages(totalPage);
			pageBean=userService.findByPage(pageBean);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			message.setFlag(false);
		}
		message.setObj(pageBean);
		return message;
	}
	
	
	
	@RequestMapping("userUpdate")
	@ResponseBody
	public Object userUpdate(User user) {
		Message message = new Message();
		try {
			userService.update(user);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			message.setFlag(false);
		}
		return message;
	}
}
