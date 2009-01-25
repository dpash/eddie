package com.sun.syndication.feed.synd;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.sun.syndication.feed.impl.CopyFromHelper;
import com.sun.syndication.feed.impl.ObjectBean;
import com.sun.syndication.feed.module.DCModule;
import com.sun.syndication.feed.module.DCModuleImpl;
import com.sun.syndication.feed.module.Module;

public class SyndEntryImpl implements Serializable, SyndEntry {
	private  List<String> authors = new LinkedList<String>();
    
	private  List<SyndCategory> 	categories = new LinkedList<SyndCategory>();
	           
	private  List<SyndContent> 	contents = new LinkedList<SyndContent>();
	           
	private  java.util.List 	_contributors;
	           
	private  SyndContent description;
	           
	private  List<SyndEnclosure> enclosures;
	           
	private  java.util.List 	_foreignMarkup;
	           
	private  String 	link;
	           
	private  List<SyndLink> links = new LinkedList<SyndLink>();
	           
	private  List<Module> modules = new LinkedList<Module>();;
	           
	private  ObjectBean 	_objBean;
	           
	private  SyndContent 	title;
	           
	private  java.util.Date 	publishedDate;
	           
	private  java.lang.String 	_uri;
	           
	static java.util.Set 	CONVENIENCE_PROPERTIES;
	          
	private static CopyFromHelper 	COPY_FROM_HELPER;
	           
	private static java.util.Set 	IGNORE_PROPERTIES ;
	public Object clone() {return this;}
	
	public String getAuthor() {
		if (this.authors.isEmpty()) { return null; }
		return (String)this.authors.get(0);

	}

	public List getAuthors() {
		return this.authors;
	}

	public List getCategories() {
		return categories;
	}

	public List<SyndContent> getContents() {
		// TODO Auto-generated method stub
		return this.contents;
	}

	public List getContributors() {
		// TODO Auto-generated method stub
		return null;
	}

	public SyndContent getDescription() {
		return this.description;
	}

	public List<SyndEnclosure> getEnclosures() {
		return enclosures;
	}

	public Object getForeignMarkup() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLink() {
		return this.link;
	}

	public List<SyndLink> getLinks() {
		return links;
	}

	public Module getModule(String uri) {
		Module ret = null;
		for (Module module: this.modules) {
			if (module.getUri().equals(uri)){
				ret = module;
				break;
			}
		}
		return ret;
	}

	public List getModules() {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getPublishedDate() {
		return this.getDCModule().getDate();
	}

	public String getTitle() {
		if (this.title == null) { return null; }
		return this.title.getValue();
	}

	public SyndContent getTitleEx() {
		return this.title;
	}

	public Date getUpdatedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUri() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAuthor(String author) {
		this.authors.add(author);
	}

	public void setAuthors(List authors) {
		// TODO Auto-generated method stub

	}

	public void setCategories(List categories) {
		this.categories = categories;

	}

	public void setContents(List<SyndContent> contents) {
		this.contents = contents;
	}

	public void setContributors(List contributors) {
		// TODO Auto-generated method stub

	}

	public void setDescription(SyndContent description) {
		this.description = description;
	}

	public void setEnclosures(List<SyndEnclosure> enclosures) {
		this.enclosures = enclosures;

	}

	public void setForeignMarkup(Object foreignMarkup) {
		// TODO Auto-generated method stub

	}

	public void setLink(String link) {
		this.link = link;

	}

	public void setLinks(List<SyndLink> links) {
		this.links = links;
	}

	public void setModules(List modules) {
		// TODO Auto-generated method stub

	}

	public void setPublishedDate(Date date) {
		this.getDCModule().setDate(date);


	}

	public void setTitle(String title) {
		// TODO Auto-generated method stub

	}

	public void setTitleEx(SyndContent title) {
		this.title = title;

	}

	public void setUpdatedDate(Date updatedDate) {
		// TODO Auto-generated method stub

	}

	public void setUri(String uri) {
		// TODO Auto-generated method stub

	}

	public void copyFrom(Object obj) {
		// TODO Auto-generated method stub

	}

	public Class getInterface() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	private DCModule getDCModule() {
		DCModule module = (DCModule)this.getModule(DCModule.URI);
		if (module == null) {
			module = new DCModuleImpl();
			this.modules.add(module);
		}
		return module;
	}
}
