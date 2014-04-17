package com.seven.idouban.model;

import java.io.Serializable;


public class Rating implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 最高评分 **/
	public int max;
	/** 最高评分float(1) **/
	public float average;
	/** 评星数 **/
	public int stars;
	/** 最低评分 **/
	public int min;
	@Override
	public String toString() {
		return "Rating [max=" + max + ", average=" + average + ", stars="
				+ stars + ", min=" + min + "]";
	}
}
