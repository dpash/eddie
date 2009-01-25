package com.sun.syndication.feed.synd;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.sun.syndication.feed.CopyFrom;
import com.sun.syndication.feed.impl.ObjectBean;

public class SyndEnclosureImpl implements Serializable, Cloneable, CopyFrom,
		SyndEnclosure {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long length;

	private ObjectBean _objBean;

	private String type;

	private String url;

	public void copyFrom(Object obj) {
		// TODO Auto-generated method stub

	}

	public Class getInterface() {
		// TODO Auto-generated method stub
		return null;
	}

	public long getLength() {
		// TODO Auto-generated method stub
		return this.length;
	}

	public String getType() {
		// TODO Auto-generated method stub
		return this.type;
	}

	public String getUrl() {
		// TODO Auto-generated method stub
		return this.url;
	}

	public void setLength(long length) {
		this.length = length;

	}

	public void setType(String type) {
		this.type = type;

	}

	public void setUrl(String url) {
		this.url = url;
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
