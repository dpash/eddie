package com.sun.syndication.feed.synd;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.sun.syndication.feed.impl.CopyFromHelper;
import com.sun.syndication.feed.impl.ObjectBean;

public class SyndContentImpl implements Serializable, SyndContent {
	private  java.lang.String 	mode;
    
	private  ObjectBean 	objBean;
	           
	private  java.lang.String 	type;
	           
	private  java.lang.String 	value;
	           
	private static CopyFromHelper 	COPY_FROM_HELPER;
	      
	public String getMode() {

		return mode;
	}

	public String getType() {

		return type;
	}

	public String getValue() {

		return value;
	}

	public void setMode(String mode) {
		this.mode = mode;

	}

	public void setType(String type) {
		this.type = type;


	}

	public void setValue(String value) {
		this.value = value;


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
		      return EqualsBuilder.reflectionEquals(this,other);
		   }
		   public String toString() {
		      return ToStringBuilder.reflectionToString(this);
		   }
}
