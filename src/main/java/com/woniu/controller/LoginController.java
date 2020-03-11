package com.woniu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public final class LoginController {
	@RequestMapping("login")
	public String login(HttpSession session) {
		return "main";
	}
}
