package com.woniu.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{
	private Integer uid;
	private Role role;
	private String uaccount;
	private String upassword;
	private String uname;
	private String uaddress;
	private String ustatus;
}
