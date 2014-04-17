package com.seven.idouban.model;

import java.io.Serializable;

/**
 * 名人
 * 
 */
public class Celebrity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 影人条目id **/
	public String id;
	
	/** 中文名 **/
	public String name;
	
	/** 影人条目URL **/
	public String alt;
	
	/** 影人头像，分别提供420px x 600px(大)，140px x 200px(中) 70px x 100px(小)尺寸 **/
	public Image avatars;

	@Override
	public String toString() {
		return "Celebrity [id=" + id + ", name=" + name + ", alt=" + alt
				+ ", avatars=" + avatars + "]";
	}
}
