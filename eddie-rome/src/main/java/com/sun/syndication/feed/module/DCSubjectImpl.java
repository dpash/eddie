package com.sun.syndication.feed.module;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.sun.syndication.feed.CopyFrom;

public class DCSubjectImpl implements Serializable, Cloneable, CopyFrom,
		DCSubject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private  String 	taxonomyUri;
    
	private  String 	value;
	           
	public void copyFrom(Object obj) {
		// TODO Auto-generated method stub

	}

	public Object clone() {return this;}
	public Class getInterface() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTaxonomyUri() {
		// TODO Auto-generated method stub
		return this.taxonomyUri;
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}

	public void setTaxonomyUri(String taxonomyUri) {
		this.taxonomyUri = taxonomyUri;

	}

	public void setValue(String value) {
		this.value = value;

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
