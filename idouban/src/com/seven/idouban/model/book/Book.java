package com.seven.idouban.model.book;

import java.util.Arrays;

import com.seven.idouban.model.Image;

public class Book {
	public int rating;
	public String subtitle;
	public String[] author;
	public String pubdate;
	public Tag[] tags;
	public String origin_title;
	public String image;
	public String binding;
	public String[] translator;
	public String catalog;
	public String pages;
	public Image images;
	public String alt;
	public String id;
	public String publisher;
	public String isbn10;
	public String isbn13;
	public String title;
	public String url;
	public String alt_title;
	public String author_intro;
	public String summary;
	public String price;
	@Override
	public String toString() {
		return "Book [rating=" + rating + ", subtitle=" + subtitle
				+ ", author=" + Arrays.toString(author) + ", pubdate="
				+ pubdate + ", tags=" + Arrays.toString(tags)
				+ ", origin_title=" + origin_title + ", image=" + image
				+ ", binding=" + binding + ", translator="
				+ Arrays.toString(translator) + ", catalog=" + catalog
				+ ", pages=" + pages + ", images=" + images + ", alt=" + alt
				+ ", id=" + id + ", publisher=" + publisher + ", isbn10="
				+ isbn10 + ", isbn13=" + isbn13 + ", title=" + title + ", url="
				+ url + ", alt_title=" + alt_title + ", author_intro="
				+ author_intro + ", summary=" + summary + ", price=" + price
				+ "]";
	}
}
