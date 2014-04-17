package com.seven.idouban.model.movie;

import java.util.Arrays;

public class SubjectsResponse {

	public int count;
	public int start;
	public int total;
	public String title;
	public SubjectS[] subjects;
	@Override
	public String toString() {
		return "SearchResponse [count=" + count + ", start=" + start
				+ ", total=" + total + ", title=" + title + ", subjects="
				+ Arrays.toString(subjects) + "]";
	}
}
