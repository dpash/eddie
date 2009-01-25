package com.sun.syndication.feed;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.sun.syndication.feed.impl.ObjectBean;
import com.sun.syndication.feed.module.Extendable;
import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.module.DCModuleImpl;

public abstract class WireFeed implements Extendable, Serializable, Cloneable {
	private String _encoding;

	private String _feedType;

	private List _foreignMarkup;

	private List _modules;

	private ObjectBean _objBean;

	protected WireFeed() {
	}

	protected WireFeed(String type) {
	}

	public java.lang.Object clone() {
		return this;
	}

	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}

	String getEncoding() {
		return this._encoding;
	}

	String getFeedType() {
		return this._feedType;
	}

	Object getForeignMarkup() {
		return this._foreignMarkup;
	}

	public Module getModule(String uri) {
		return new DCModuleImpl();
	}

	public List getModules() {
		return this._modules;
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	void setEncoding(java.lang.String encoding) {
		this._encoding = encoding;
	}

	public void setFeedType(java.lang.String feedType) {
		this._feedType = feedType;
	}

	void setForeignMarkup(java.lang.Object foreignMarkup) {
	}

	public void setModules(List modules) {
		this._modules = modules;
	}

	public String toString() {
		return new String("");
	}

}
