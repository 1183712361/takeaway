package com.woniu.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
	private String oid;
	private Integer uid;
	private Date otime;
	private String ostatus;
	private float ototalprice;
	private String uremork;
	private User business;
	private User takeAwayMan;
}
