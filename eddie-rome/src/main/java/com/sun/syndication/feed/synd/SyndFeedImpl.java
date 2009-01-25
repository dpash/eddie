package com.sun.syndication.feed.synd;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Collections;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.impl.CopyFromHelper;
import com.sun.syndication.feed.impl.ObjectBean;
import com.sun.syndication.feed.module.DCModule;
import com.sun.syndication.feed.module.DCModuleImpl;
import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.module.SyModule;
import com.sun.syndication.feed.module.SyModuleImpl;
import com.sun.syndication.feed.synd.impl.Converters;

public class SyndFeedImpl implements SyndFeed,  Serializable {

	private List<String> authors = new LinkedList<String>();

	private List _contributors;

	private SyndContent description;

	private String _encoding;

	private List entries;

	private String feedType;

	private List _foreignMarkup = new LinkedList();

	private SyndImage image;

	private String link;

	private List _links;

	private List<Module> modules = new LinkedList<Module>();

	private ObjectBean _objBean;

	private SyndContent title;

	private String uri;

	private static final Converters CONVERTERS = new Converters();

	private static final CopyFromHelper COPY_FROM_HELPER;

	private static final Set IGNORE_PROPERTIES = new HashSet();

	static Set CONVENIENCE_PROPERTIES = Collections
			.unmodifiableSet(IGNORE_PROPERTIES);

	public SyndFeedImpl() {
	}

	protected SyndFeedImpl(Class beanClass, Set convenienceProperties) {
	}

	public SyndFeedImpl(WireFeed feed) {
	}

	public Object clone() {
		return this;
	}

	public void copyFrom(Object obj) {
	}

	public WireFeed createWireFeed() {
		WireFeed feed = new Feed();
		return feed;
	}

	public WireFeed createWireFeed(String feedType) {
		WireFeed feed = new Feed();
		return feed;
	}

	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this,other);
	}

	public String getAuthor() {
		if (this.authors.isEmpty()) { return null; }
		return (String)this.authors.get(0);
	}

	public List getAuthors() {
		return this.authors;
	}

	public List getCategories() {
		return this.getDCModule().getSubjects();
	}

	public List getContributors() {
		return this._contributors;
	}

	public String getCopyright() {
		return this.getDCModule().getRights();
	}

	private DCModule getDCModule() {
		DCModule module = (DCModule)this.getModule(DCModule.URI);
		if (module == null) {
			module = new DCModuleImpl();
			this.modules.add(module);
		}
		return module;
	}

	public String getDescription() {
		if (description == null) { return null;}
		return description.getValue();
	}

	public SyndContent getDescriptionEx() {
		return description;
	}

	public String getEncoding() {
		return this._encoding;
	}

	public List getEntries() {
		return this.entries;
	}

	public String getFeedType() {
		return this.feedType;
	}

	public Object getForeignMarkup() {
		return this._foreignMarkup;
	}

	public SyndImage getImage() {
		return this.image;
	}

	public Class getInterface() {
		return null;
	}

	public String getLanguage() {
		return this.getDCModule().getLanguage();
	}

	public String getLink() {
		return this.link;
	}

	public List getLinks() {
		return this._links;
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
		return this.modules;
	}

	public Date getPublishedDate() {
		return this.getDCModule().getDate();
	}

	public List getSupportedFeedTypes() {
		return null;
	}

	public String getTitle() {
		if (this.title == null) { return null; }
		return this.title.getValue();
	}

	public SyndContent getTitleEx() {
		return this.title;
	}

	public String getUri() {
		return this.uri;
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public void setAuthor(String author) {
		this.authors.add(author);
	}

	public void setAuthors(List authors) {
	}

	public void setCategories(List categories) {
		this.getDCModule().setSubjects(categories);
	}

	public void setContributors(List contributors) {
	}

	public void setCopyright(String copyright) {
		this.getDCModule().setRights(copyright);
	}

	public void setDescription(String description) {
		if (this.description == null) {
			this.description = new SyndContentImpl();
		}
		this.description.setValue(description);
	}

	public void setDescriptionEx(SyndContent description) {
		this.description = description;
	}

	public void setEncoding(String encoding) {
	}

	public void setEntries(List entries) {
		this.entries = entries;
	}

	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}

	public void setForeignMarkup(Object foreignMarkup) {
	}

	public void setImage(SyndImage image) {
		this.image = image;
	}

	public void setLanguage(String language) {
		this.getDCModule().setLanguage(language);
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setLinks(List links) {
	}

	public void setModules(List modules) {
	}

	public void setPublishedDate(Date date) {
		this.getDCModule().setDate(date);
	}

	public void setTitle(String title) {
	}

	public void setTitleEx(SyndContent title) {
		this.title = title;
	}

	public void setUri(String uri) {
		this.uri =uri;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);

	}
    static {
        Map<String, Class> basePropInterfaceMap = new HashMap<String, Class>();
        basePropInterfaceMap.put("feedType",String.class);
        basePropInterfaceMap.put("encoding",String.class);
        basePropInterfaceMap.put("uri",String.class);
        basePropInterfaceMap.put("title",String.class);
        basePropInterfaceMap.put("link",String.class);
        basePropInterfaceMap.put("description",String.class);
        basePropInterfaceMap.put("image",SyndImage.class);
        basePropInterfaceMap.put("entries",SyndEntry.class);
        basePropInterfaceMap.put("modules",Module.class);

        Map<Class,Class> basePropClassImplMap = new HashMap<Class,Class>();
        basePropClassImplMap.put(SyndEntry.class,SyndEntryImpl.class);
        basePropClassImplMap.put(SyndImage.class,SyndImageImpl.class);
        basePropClassImplMap.put(DCModule.class,DCModuleImpl.class);
        basePropClassImplMap.put(SyModule.class,SyModuleImpl.class);

        COPY_FROM_HELPER = new CopyFromHelper(SyndFeed.class,basePropInterfaceMap,basePropClassImplMap);
    }
}
