package com.sun.syndication.feed.synd;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class SyndImageImpl implements SyndImage, Serializable {
	private String description;

	private String link;

	private String title;

	private String url;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Object clone() {
		return this;
	}

	public String getDescription() {
		return description;
	}

	public String getLink() {
		return link;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public void setDescription(String description) {
		this.description = description;

	}

	public void setLink(String link) {
		this.link = link;

	}

	public void setTitle(String title) {
		this.title = title;

	}

	public void setUrl(String url) {
		this.url = url;

	}

	public void copyFrom(Object obj) {
		// TODO Auto-generated method stub

	}

	public Class getInterface() {
		// TODO Auto-generated method stub
		return null;
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
