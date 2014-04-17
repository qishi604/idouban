package com.seven.idouban.model.book;

import java.io.Serializable;

public class Tag implements Serializable {

	private static final long serialVersionUID = 1L;
	public int count;
	public String name;
	public String title;
	@Override
	public String toString() {
		return "Tag [count=" + count + ", name=" + name + ", title=" + title
				+ "]";
	}
}
