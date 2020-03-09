package com.woniu.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
	private Integer currentPage;
	private Integer pageSize;
	private Integer pages;
	private Integer totalCount;
	private List<T> data;
	private String queryVal;
}
