package com.sun.syndication.feed.synd;

import com.sun.syndication.feed.CopyFrom;

public interface SyndLink extends CopyFrom, Cloneable {
	Object clone();

	boolean equals(Object other);

	String getHref();

	String getHreflang();

	long getLength();

	String getRel();

	String getTitle();

	String getType();

	int hashCode();

	void setHref(String href);

	void setHreflang(String hreflang);

	void setLength(long length);

	void setRel(String rel);

	void setTitle(String title);

	void setType(String type);

	String toString();
}
