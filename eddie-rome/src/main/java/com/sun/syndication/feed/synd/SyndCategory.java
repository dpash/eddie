package com.sun.syndication.feed.synd;

public interface SyndCategory extends Cloneable {
	Object clone();

	String getName();

	String getTaxonomyUri();

	void setName(String name);

	void setTaxonomyUri(String taxonomyUri);
}
