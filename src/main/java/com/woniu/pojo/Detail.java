package com.woniu.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Detail implements Serializable{
	private String did;
	private Orders oriders;
	private Menu mean;
	private float univalent;
	private Integer amount;
}
