package com.woniu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
	private Integer mid;
	private MenuType mt;
	private String mname;
	private float mprice;
	private User business;
	
}
