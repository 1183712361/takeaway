package com.woniu.controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniu.pojo.Message;
import com.woniu.pojo.Orders;
import com.woniu.pojo.User;
import com.woniu.service.IOrdersSercvice;


@Controller
@RequestMapping("orders")
public class OrdersController {
	@Autowired
	IOrdersSercvice orderService;
	
	@RequestMapping("index")
	public String index() {
		return "order/index";
	}
	
	@RequestMapping("addOrder")
	@ResponseBody
	public Object addOrder(Integer mid,Integer bid,float mprice) {
		Message message = new Message();
		try {
			Orders orders = new Orders();
			Date date = new Date();
			UUID oid = UUID.randomUUID();
			orders.setOid(oid.toString());
			orders.setOtime(date);
			User user = new User();
			user.setUid(bid);
			orders.setBusiness(user);;
			orders.setOtotalprice(mprice);
			orders.setOstatus("未确认");
			//模拟增加，最后改为从session中获取
			orders.setUid(1);
			orderService.save(orders);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			message.setFlag(false);
		}
		return message;
	}
}
