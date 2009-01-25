package com.sun.syndication.feed.synd;

import com.sun.syndication.feed.CopyFrom;

public interface SyndImage extends Cloneable, CopyFrom {
	Object clone();

	String getDescription();

	String getLink();

	String getTitle();

	String getUrl();

	void setDescription(String description);

	void setLink(String link);

	void setTitle(String title);

	void setUrl(String url);
}
