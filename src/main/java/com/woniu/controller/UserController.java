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
	/**
	 * 模拟登录，登录部分直接删掉就行。
	 * 1、用户展示页面，如果rid角色不是管理员直接跳到菜单页面
	 * @param session
	 * @return
	 */
	@RequestMapping("index")//用户展示
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
	
	/**
	 * user目录下，点击个人信息可以查看个人信息
	 * @param uid
	 * @param model
	 * @return
	 */
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
	
	
	/**
	 * 修改个人信息
	 * @param user
	 * @return
	 */
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
