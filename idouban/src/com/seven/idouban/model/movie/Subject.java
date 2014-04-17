package com.seven.idouban.model.movie;

import java.io.Serializable;
import java.util.Arrays;

import com.seven.idouban.model.Celebrity;
import com.seven.idouban.model.Image;
import com.seven.idouban.model.Rating;

public class Subject implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 评分 **/
	public Rating rating;
	/** 影评数量 **/
	public Integer reviews_count;
	/** 想看人数 **/
	public Integer wish_count;
	/** 看过人数 **/
	public Integer collect_count;
	/** 豆瓣小站 **/
	public String douban_site;
	/** 年代 **/
	public String year;
	/** 图片 **/
	public Image images;
	/** 年条目页url **/
	public String alt;
	/** 条目id **/
	public String id;
	/** 移动版条目页URL **/
	public String mobile_url;
	/** 中文名 **/
	public String title;
	/** 在看人数，如果是电视剧，默认值为0，如果是电影值为null **/
	public Integer do_count;
	/** 总季数(tv only) **/
	public Integer seasons_count;
	/** 影讯页URL(movie only) **/
	public String schedule_url;
	/** 当前季的集数(tv only) **/
	public Integer episodes_count;
	/** 影片类型，最多提供3个 **/
	public String[] genres;
	/** 制片国家/地区 **/
	public String[] countries;
	/** 主演，最多可获得4个，数据结构为影人的简化描述 **/
	public Celebrity[] casts;
	/** 当前季数(tv only) **/
	public Integer current_season;
	/** 原名 **/
	public String original_title;
	/** 简介 **/
	public String summary;
	/** 条目分类, movie或者tv **/
	public String subtype;
	/** 导演，数据结构为影人的简化描述 **/
	public Celebrity[] directors;
	/** 短评数量 **/
	public Integer comments_count;
	/** 评分人数 **/
	public Integer ratings_count;
	/** 更多中文名 **/
	public String[] aka;
	/** 更多英文名 **/
	public String[] aka_en;
	@Override
	public String toString() {
		return "Movie [rating=" + rating + ", reviews_count=" + reviews_count
				+ ", wish_count=" + wish_count + ", collect_count="
				+ collect_count + ", douban_site=" + douban_site + ", year="
				+ year + ", images=" + images + ", alt=" + alt + ", id=" + id
				+ ", mobile_url=" + mobile_url + ", title=" + title
				+ ", do_count=" + do_count + ", seasons_count=" + seasons_count
				+ ", schedule_url=" + schedule_url + ", episodes_count="
				+ episodes_count + ", genres=" + Arrays.toString(genres)
				+ ", countries=" + Arrays.toString(countries) + ", casts="
				+ Arrays.toString(casts) + ", current_season=" + current_season
				+ ", original_title=" + original_title + ", summary=" + summary
				+ ", subtype=" + subtype + ", directors="
				+ Arrays.toString(directors) + ", comments_count="
				+ comments_count + ", ratings_count=" + ratings_count
				+ ", aka=" + Arrays.toString(aka) + ", aka_en="
				+ Arrays.toString(aka_en) + "]";
	}
}
