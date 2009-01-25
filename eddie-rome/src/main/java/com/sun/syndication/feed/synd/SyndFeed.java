package com.sun.syndication.feed.synd;

import com.sun.syndication.feed.CopyFrom;
import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.module.Extendable;
import com.sun.syndication.feed.module.Module;

public interface SyndFeed extends Cloneable, CopyFrom, Extendable {
	Object clone();

	WireFeed createWireFeed();

	WireFeed createWireFeed(java.lang.String feedType);

	java.lang.String getAuthor();

	java.util.List getAuthors();

	java.util.List getCategories();

	java.util.List getContributors();

	java.lang.String getCopyright();

	java.lang.String getDescription();

	SyndContent getDescriptionEx();

	java.lang.String getEncoding();

	java.util.List getEntries();

	java.lang.String getFeedType();

	java.lang.Object getForeignMarkup();

	SyndImage getImage();

	java.lang.String getLanguage();

	java.lang.String getLink();

	java.util.List getLinks();

	Module getModule(String uri);

	java.util.List getModules();

	java.util.Date getPublishedDate();

	java.util.List getSupportedFeedTypes();

	String getTitle();

	SyndContent getTitleEx();

	String getUri();

	void setAuthor(String author);

	void setAuthors(java.util.List authors);

	void setCategories(java.util.List categories);

	void setContributors(java.util.List contributors);

	void setCopyright(String copyright);

	void setDescription(String description);

	void setDescriptionEx(SyndContent description);

	void setEncoding(String encoding);

	void setEntries(java.util.List entries);

	void setFeedType(String feedType);

	void setForeignMarkup(Object foreignMarkup);

	void setImage(SyndImage image);

	void setLanguage(String language);

	void setLink(String link);

	void setLinks(java.util.List links);

	void setModules(java.util.List modules);

	void setPublishedDate(java.util.Date publishedDate);

	void setTitle(String title);

	void setTitleEx(SyndContent title);

	void setUri(String uri);

}
