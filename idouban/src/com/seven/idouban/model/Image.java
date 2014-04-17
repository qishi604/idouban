package com.seven.idouban.model;

import java.io.Serializable;

public class Image implements Serializable {

	private static final long serialVersionUID = 1L;
	public String small;
	public String large;
	public String medium;
	@Override
	public String toString() {
		return "Image [small=" + small + ", large=" + large + ", medium="
				+ medium + "]";
	}
}
