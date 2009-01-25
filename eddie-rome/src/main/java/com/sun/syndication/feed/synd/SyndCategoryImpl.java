package com.sun.syndication.feed.synd;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.sun.syndication.feed.module.DCSubject;
import com.sun.syndication.feed.module.DCSubjectImpl;

public class SyndCategoryImpl implements Serializable, Cloneable, SyndCategory {
	private  DCSubject 	subject;
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SyndCategoryImpl() {
		subject = new DCSubjectImpl();
	}

	public SyndCategoryImpl(DCSubject subject) {
		this.subject = subject;
	}
	DCSubject getSubject() {
		return subject;
	}
	public String getName() {
		// TODO Auto-generated method stub
		return subject.getValue();
	}

	public String getTaxonomyUri() {
		// TODO Auto-generated method stub
		return subject.getTaxonomyUri();
	}

	public void setName(String name) {
		this.subject.setValue(name);

	}

	public void setTaxonomyUri(String taxonomyUri) {
		this.subject.setTaxonomyUri(taxonomyUri);

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
