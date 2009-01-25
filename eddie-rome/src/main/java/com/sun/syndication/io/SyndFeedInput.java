package com.sun.syndication.io;

import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import uk.org.catnip.eddie.Author;
import uk.org.catnip.eddie.Category;
import uk.org.catnip.eddie.Detail;
import uk.org.catnip.eddie.Enclosure;
import uk.org.catnip.eddie.Entry;
import uk.org.catnip.eddie.FeedData;
import uk.org.catnip.eddie.Image;
import uk.org.catnip.eddie.Link;
import uk.org.catnip.eddie.parser.Parser;

import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEnclosureImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.feed.synd.SyndImage;
import com.sun.syndication.feed.synd.SyndImageImpl;
import com.sun.syndication.feed.synd.SyndLink;
import com.sun.syndication.feed.synd.SyndLinkImpl;

public class SyndFeedInput {
	private static Map<String,String> formatmap = new HashMap<String,String>();
	static {
		formatmap.put("atom03","atom_0.3");
		formatmap.put("atom10","atom_1.0");
		formatmap.put("rss090","rss_0.9");
		formatmap.put("rss091n", "rss_0.91N");
		formatmap.put("rss091u", "rss_0.91U");
		formatmap.put("rss092", "rss_0.92");
		formatmap.put("rss093", "rss_0.93");
		formatmap.put("rss094", "rss_0.94");
		formatmap.put("rss10", "rss_1.0");
		formatmap.put("rss20", "rss_2.0");
	}
	private static Map<String,String> typemap = new HashMap<String,String>();
	static {
		typemap.put("text/html","html");
		typemap.put("text/plain", "text");
	}
	public SyndFeed build(Reader reader) {
	
		
	Parser parser = new Parser();
		
		FeedData feed = parser.parse(reader);
		return feedDataToSyndFeed(feed);
	}
	private SyndFeed feedDataToSyndFeed(FeedData feed) {
		SyndFeed syndfeed = new SyndFeedImpl();
		syndfeed.setFeedType(formatmap.get(feed.get("format")));
		syndfeed.setLink(feed.get("link"));
		syndfeed.setAuthor(convertAuthorToString(feed.getAuthor()));
		syndfeed.setCopyright(feed.get("copyright"));
		syndfeed.setLanguage(feed.get("language"));
		syndfeed.setPublishedDate(feed.getModified());
		syndfeed.setImage(convertImageToSyndImage(feed.getImage()));
		syndfeed.setTitleEx(convertDetailToSyndContent(feed.getTitle()));
		syndfeed.setCategories(convertCategoriesToSyndCategories(feed.getCategories()));
		syndfeed.setDescriptionEx(convertDetailToSyndContent(feed.getSubtitle()));
		syndfeed.setImage(convertImagetoSyndImage(feed.getImage()));
		if (null != feed.get("guid")) {
		syndfeed.setUri(feed.get("guid"));
		} else {
			syndfeed.setUri(feed.get("uri"));
		}
		List<SyndEntry> entries = new LinkedList<SyndEntry>();
		for (Entry entry: feed.getEntries()) {
			entries.add(convertEntryToSyndEntry(entry));
		}
		syndfeed.setEntries(entries);
		return syndfeed;
	}
	
	private SyndImage convertImagetoSyndImage(Image image) {
		if (image == null) { return null; }
		SyndImage syndimage = new SyndImageImpl();
		syndimage.setDescription(image.getDescription());
		syndimage.setLink(image.getLink());
		syndimage.setTitle(image.getTitle());
		syndimage.setUrl(image.getUrl());
		
		return syndimage;
	}
	private List convertCategoriesToSyndCategories(List<Category> categories) {
		List<SyndCategory> syndcategories = new LinkedList<SyndCategory>();
		for (Category category: categories) {
			syndcategories.add(convertCategoryToSyndCategory(category));
		}
		return syndcategories;
	}
	private SyndCategory convertCategoryToSyndCategory(Category category) {
		if (category == null) {return null; }
		SyndCategory syndcategory = new SyndCategoryImpl();
		syndcategory.setName(category.getTerm());
		syndcategory.setTaxonomyUri(category.getSchedule());
		return syndcategory;

	}
	private String convertAuthorToString(Author author) {
		if (author == null) { return null; }
		return author.getName();
	}
	private SyndImage convertImageToSyndImage(Image image) {
		if (image == null) {return null; }
		SyndImage syndimage = new SyndImageImpl();
		syndimage.setTitle(image.getTitle());
		return syndimage;
	}
	
	private SyndEntry convertEntryToSyndEntry(Entry entry) {
		SyndEntry syndentry = new SyndEntryImpl();
		syndentry.setTitleEx(convertDetailToSyndContent(entry.getTitle()));
		syndentry.setLink(entry.get("link"));
		syndentry.setLinks(convertLinksToSyndLinks(entry.getLinks()));
		syndentry.setAuthor(convertAuthorToString(entry.getAuthor()));
		if (entry.getCreated() != null) {
		syndentry.setPublishedDate(entry.getCreated());
		} else {
		syndentry.setPublishedDate(entry.getModified());
		}
		syndentry.setDescription(convertDetailToSyndContent(entry.getSummary()));
		syndentry.setContents(convertDetailsToSyndContents(entry.getContents()));
		syndentry.setEnclosures(convertEnclosuresToSyndEnclosures(entry.getEnclosures()));
		syndentry.setCategories(convertCategoriesToSyndCategories(entry.getCategories()));
		return syndentry;
	}
	private List<SyndLink> convertLinksToSyndLinks(List<Link> links) {
		List<SyndLink> syndlinks = new LinkedList<SyndLink>();
		for (Link link: links) {
			syndlinks.add(convertLinkToSyndLink(link));
		}
		return syndlinks;
	}
	private SyndLink convertLinkToSyndLink(Link link) {
		if (link == null) {return null; }
		SyndLink syndlink = new SyndLinkImpl();
		syndlink.setHref(link.getHref());
		syndlink.setHreflang(link.getHreflang());
		if (link.getLength() != null) {
			syndlink.setLength(Long.parseLong(link.getLength()));
		}
		syndlink.setRel(link.getRel());
		syndlink.setTitle(link.getTitle());
		syndlink.setType(link.getType());
		return syndlink;
	}
	private List<SyndEnclosure> convertEnclosuresToSyndEnclosures(List<Enclosure> enclosures) {

		List<SyndEnclosure> syndenclosures = new LinkedList<SyndEnclosure>();
		for (Enclosure enclosure: enclosures) {
			syndenclosures.add(convertEnclosureToSyndEnclosure(enclosure));
		}
		return syndenclosures;
	}
	private SyndEnclosure convertEnclosureToSyndEnclosure(Enclosure enclosure) {
		if (enclosure == null) {return null; }
		SyndEnclosure syndenclosure = new SyndEnclosureImpl();
		if (enclosure.getLength() != null) {
		syndenclosure.setLength(Long.parseLong(enclosure.getLength()));
		}
		syndenclosure.setType(enclosure.getType());
		syndenclosure.setUrl(enclosure.getUrl());
		return syndenclosure;
	}
	private List<SyndContent> convertDetailsToSyndContents(List<Detail> details) {
		List<SyndContent> syndcontents = new LinkedList<SyndContent>();
		for (Detail detail: details) {
			syndcontents.add(convertDetailToSyndContent(detail));
		}
		return syndcontents;
	}
	private SyndContent convertDetailToSyndContent(Detail detail) {
		if (detail == null) { return null; }
		SyndContent content = new SyndContentImpl();
		content.setType(detail.getType());
		if (detail.getType() != null) {
			content.setMode(typemap.get(detail.getType()));
		}
		content.setValue(detail.getValue());
		return content;
	}
}
