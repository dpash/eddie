package com.sun.syndication.io;

import org.jdom.Document;

import com.sun.syndication.feed.WireFeed;

public interface WireFeedGenerator {
	Document 	generate(WireFeed feed);
    String 	getType();
}
