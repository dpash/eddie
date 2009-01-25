package com.sun.syndication.feed.synd;

import com.sun.syndication.feed.CopyFrom;

public interface SyndContent extends Cloneable, CopyFrom {
	Object clone();

	String getMode();

	String getType();

	String getValue();

	void setMode(String mode);

	void setType(String type);

	void setValue(String value);
}
