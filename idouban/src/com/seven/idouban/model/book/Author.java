package com.seven.idouban.model.book;

import java.io.Serializable;

public class Author implements Serializable {

	private static final long serialVersionUID = 1L;
	public String name;
	public boolean is_banned;
	public boolean is_suicide;
	public String url;
	public String avatar;
	public String uid;
	public String alt;
	public String type;
	public String id;
	public String large_avatar;
	public String toString() {
		return "Author [name=" + name + ", is_banned=" + is_banned
				+ ", is_suicide=" + is_suicide + ", url=" + url + ", avatar="
				+ avatar + ", uid=" + uid + ", alt=" + alt + ", type=" + type
				+ ", id=" + id + ", large_avatar=" + large_avatar + "]";
	}

}
