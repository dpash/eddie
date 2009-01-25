package com.sun.syndication.feed.synd;

import com.sun.syndication.feed.CopyFrom;

public interface SyndEnclosure extends CopyFrom, Cloneable {
	long getLength();

	String getType();

	String getUrl();

	void setLength(long length);

	void setType(String type);

	void setUrl(String url);
}
