package com.sun.syndication.feed.atom;

import java.io.Serializable;

import com.sun.syndication.feed.impl.ObjectBean;

public class Generator implements Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private  ObjectBean 	objBean;
    
	private  String 	url;
	           
	private  String 	value;
	           
	private  String 	version;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	      

}
