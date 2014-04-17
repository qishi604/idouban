package com.seven.idouban.model.movie;

import java.io.Serializable;

import com.seven.idouban.model.Image;
import com.seven.idouban.model.Rating;

/**
 * Subject 精简版
 *
 */
public class SubjectS implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 评分 **/
	public Rating rating;
	public Integer collect_count;
	/** 年代 **/
	public String year;
	/** 图片 **/
	public Image images;
	/** 年条目页url **/
	public String alt;
	/** 条目id **/
	public String id;
	/** 中文名 **/
	public String title;
	/** 影讯页URL(movie only) **/
	/** 原名 **/
	public String original_title;
	/** 条目分类, movie或者tv **/
	public String subtype;
	@Override
	public String toString() {
		return "SubjectS [rating=" + rating + ", collect_count="
				+ collect_count + ", year=" + year + ", images=" + images
				+ ", alt=" + alt + ", id=" + id + ", title=" + title
				+ ", original_title=" + original_title + ", subtype=" + subtype
				+ "]";
	}
}
