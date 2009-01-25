package com.sun.syndication.feed.synd;

import com.sun.syndication.feed.WireFeed;

public interface Converter {
	void copyInto(WireFeed feed, SyndFeed syndFeed);

	WireFeed createRealFeed(SyndFeed syndFeed);

	String getType();
}
