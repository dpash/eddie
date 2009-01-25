package com.sun.syndication.io;

import org.jdom.Document;

import com.sun.syndication.feed.WireFeed;

public interface WireFeedParser {

	java.lang.String getType();

	boolean isMyType(Document document);

	WireFeed parse(Document document, boolean validate);
}
