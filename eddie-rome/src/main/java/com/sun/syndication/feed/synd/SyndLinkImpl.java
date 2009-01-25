package com.sun.syndication.feed.synd;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.sun.syndication.feed.impl.ObjectBean;

public class SyndLinkImpl implements SyndLink {
	private String href;

	private String hreflang;

	private long length;

	private ObjectBean _objBean;

	private String rel;

	private String title;

	private String type;

	public String getHref() {
		return this.href;
	}

	public String getHreflang() {
		return this.hreflang;
	}

	public long getLength() {

		return this.length;
	}

	public String getRel() {
		return this.rel;
	}

	public String getTitle() {
		return this.title;
	}

	public String getType() {
		return this.type;
	}

	public void setHref(String href) {
		this.href = href;

	}

	public void setHreflang(String hreflang) {
		this.hreflang = hreflang;

	}

	public void setLength(long length) {
		this.length = length;

	}

	public void setRel(String rel) {
		this.rel = rel;

	}

	public void setTitle(String title) {
		this.title = title;

	}

	public void setType(String type) {
		this.type = type;

	}

	public void copyFrom(Object obj) {
		// TODO Auto-generated method stub

	}

	public Class getInterface() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object clone() {
		return this;
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
