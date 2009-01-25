package com.sun.syndication.feed.atom;

import java.io.Serializable;

import com.sun.syndication.feed.impl.ObjectBean;

public class Content implements Cloneable, Serializable {
	private  java.lang.String 	mode;
    
	private  ObjectBean 	objBean;
	           
	private  java.lang.String 	src;
	           
	private  java.lang.String 	type;
	           
	private  java.lang.String 	value;
	           

	           
	private static java.util.Set 	MODES;
	           

	public static final java.lang.String 	BASE64 =	"base64";
	public static final java.lang.String 	ESCAPED =	"escaped";
	public static final java.lang.String 	HTML =	"html";
	public static final java.lang.String 	TEXT =	"text";
	public static final java.lang.String 	XHTML =	"xhtml";
	public static final java.lang.String 	XML =	"xml";
	public java.lang.String getMode() {
		return mode;
	}

	public void setMode(java.lang.String mode) {
		this.mode = mode;
	}

	public java.lang.String getSrc() {
		return src;
	}

	public void setSrc(java.lang.String src) {
		this.src = src;
	}

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getValue() {
		return value;
	}

	public void setValue(java.lang.String value) {
		this.value = value;
	} 
}
