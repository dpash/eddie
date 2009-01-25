package com.sun.syndication.feed.module;

import com.sun.syndication.feed.CopyFrom;

public interface DCSubject extends CopyFrom, Cloneable {
	Object clone();

	String getTaxonomyUri();

	String getValue();

	void setTaxonomyUri(String taxonomyUri);

	void setValue(String value);
}
