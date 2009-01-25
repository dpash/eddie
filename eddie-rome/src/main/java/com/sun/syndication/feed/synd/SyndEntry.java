package com.sun.syndication.feed.synd;

import java.util.List;

import com.sun.syndication.feed.CopyFrom;
import com.sun.syndication.feed.module.Extendable;
import com.sun.syndication.feed.module.Module;

public interface SyndEntry extends Cloneable, CopyFrom, Extendable {
	java.lang.Object clone();

	java.lang.String getAuthor();

	java.util.List getAuthors();

	java.util.List getCategories();

	java.util.List getContents();

	java.util.List getContributors();

	SyndContent getDescription();

	List<SyndEnclosure> getEnclosures();

	java.lang.Object getForeignMarkup();

	java.lang.String getLink();

	List<SyndLink> getLinks();

	Module getModule(java.lang.String uri);

	java.util.List getModules();

	java.util.Date getPublishedDate();

	java.lang.String getTitle();

	SyndContent getTitleEx();

	java.util.Date getUpdatedDate();

	java.lang.String getUri();

	void setAuthor(java.lang.String author);

	void setAuthors(java.util.List authors);

	void setCategories(java.util.List categories);

	void setContents(List<SyndContent> contents);

	void setContributors(java.util.List contributors);

	void setDescription(SyndContent description);

	void setEnclosures(List<SyndEnclosure> enclosures);

	void setForeignMarkup(java.lang.Object foreignMarkup);

	void setLink(java.lang.String link);

	void setLinks(List<SyndLink> links);

	void setModules(java.util.List modules);

	void setPublishedDate(java.util.Date publishedDate);

	void setTitle(java.lang.String title);

	void setTitleEx(SyndContent title);

	void setUpdatedDate(java.util.Date updatedDate);

	void setUri(java.lang.String uri);
}
