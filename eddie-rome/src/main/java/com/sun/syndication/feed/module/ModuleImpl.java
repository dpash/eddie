package com.sun.syndication.feed.module;

import java.io.Serializable;

import com.sun.syndication.feed.impl.ObjectBean;

public class ModuleImpl implements Cloneable, Serializable, Module {
	private ObjectBean _objBean;

	private String uri;

	protected ModuleImpl(java.lang.Class beanClass, java.lang.String uri){
		this.uri = uri;
	}

	public String getUri() {
		// TODO Auto-generated method stub
		return uri;
	}

	public Object clone() {
		return this;
	}

	public boolean equals(java.lang.Object other) {
		return true;
	}

	public void copyFrom(Object obj) {
		// TODO Auto-generated method stub

	}

	public Class getInterface() {
		// TODO Auto-generated method stub
		return null;
	}

	public int hashCode() {
		return 1;
	}

	public String toString() {
		return new String("");
	}
}
