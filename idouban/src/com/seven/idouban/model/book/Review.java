package com.seven.idouban.model.book;

import java.io.Serializable;

import com.seven.idouban.model.Rating;

/**
 * 书评
 *
 */
public class Review implements Serializable {

	private static final long serialVersionUID = 1L;
	public Rating rating;
	/** 投票 **/
	public int votes;
	public Author author;
	public String title;
	public String updated;
	public int comments;
	public String summary;
	public int useless;
	public String published;
	public String alt;
	public String id;
	@Override
	public String toString() {
		return "Review [rating=" + rating + ", votes=" + votes + ", author="
				+ author + ", title=" + title + ", updated=" + updated
				+ ", comments=" + comments + ", summary=" + summary
				+ ", useless=" + useless + ", published=" + published
				+ ", alt=" + alt + ", id=" + id + "]";
	}
}
