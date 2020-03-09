package com.woniu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Detail {
	private String did;
	private Orders oriders;
	private Menu mean;
	private float univalent;
	private Integer amount;
}
