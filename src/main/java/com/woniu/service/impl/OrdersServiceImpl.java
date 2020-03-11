package com.woniu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniu.dao.OrdersDao;
import com.woniu.pojo.Orders;
import com.woniu.service.IOrdersSercvice;
@Service
@Transactional
public class OrdersServiceImpl implements IOrdersSercvice {
	@Autowired
	OrdersDao orderDao;
	@Override
	public void save(Orders orders) {
		// TODO Auto-generated method stub
		orderDao.save(orders);
	}

}
