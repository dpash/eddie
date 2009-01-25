package com.sun.syndication.io;

import java.io.Reader;

import uk.org.catnip.eddie.FeedData;
import uk.org.catnip.eddie.parser.Parser;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.atom.Feed;


public class WireFeedInput {
	public WireFeed build(Reader reader) {
		Parser parser = new Parser();
		
		FeedData feed = parser.parse(reader);
		return feedDataToWireFeed(feed);

	}
	private WireFeed feedDataToWireFeed(FeedData feed) {
		WireFeed wirefeed = new Feed();
		return wirefeed;
	}

}
