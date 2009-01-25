package com.sun.syndication.feed.module;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.sun.syndication.feed.impl.ObjectBean;

public class DCModuleImpl extends ModuleImpl implements DCModule {
	private ObjectBean _objBean;

	private List titles = new LinkedList();

	private List _creator = new LinkedList();

	private List<DCSubject> subjects = new LinkedList<DCSubject>();

	private List _description = new LinkedList();

	private List _publisher = new LinkedList();

	private List _contributors = new LinkedList();

	private List<Date> dates = new LinkedList<Date>();

	private List _type = new LinkedList();

	private List _format = new LinkedList();

	private List _identifier = new LinkedList();

	private List _source = new LinkedList();

	private List<String> language = new LinkedList<String>();

	private List _relation = new LinkedList();

	private List _coverage = new LinkedList();

	private List<String> rights = new LinkedList<String>();

	private static final Set<String> IGNORE_PROPERTIES = new HashSet<String>();
    public static final Set CONVENIENCE_PROPERTIES = Collections.unmodifiableSet(IGNORE_PROPERTIES);

	static {
		IGNORE_PROPERTIES.add("title");
		IGNORE_PROPERTIES.add("creator");
		IGNORE_PROPERTIES.add("subject");
		IGNORE_PROPERTIES.add("description");
		IGNORE_PROPERTIES.add("publisher");
		IGNORE_PROPERTIES.add("contributor");
		IGNORE_PROPERTIES.add("date");
		IGNORE_PROPERTIES.add("type");
		IGNORE_PROPERTIES.add("format");
		IGNORE_PROPERTIES.add("identifier");
		IGNORE_PROPERTIES.add("source");
		IGNORE_PROPERTIES.add("language");
		IGNORE_PROPERTIES.add("relation");
		IGNORE_PROPERTIES.add("coverage");
		IGNORE_PROPERTIES.add("rights");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DCModuleImpl() {
		super(DCModule.class, URI);
		_objBean = new ObjectBean(DCModule.class, this, CONVENIENCE_PROPERTIES);
	}

	public String getContributor() {
		// TODO Auto-generated method stub
		return null;
	}

	public List getContributors() {
		// TODO Auto-generated method stub
		return this._contributors;
	}

	public String getCoverage() {
		// TODO Auto-generated method stub
		return null;
	}

	public List getCoverages() {
		// TODO Auto-generated method stub
		return this._coverage;
	}

	public String getCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	public List getCreators() {
		// TODO Auto-generated method stub
		return this._creator;
	}

	public Date getDate() {
		Date date = null;
		if (!this.dates.isEmpty()) {
			date = this.dates.get(0);
		}
		return date;
	}

	public List getDates() {
		// TODO Auto-generated method stub
		return this.dates;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	public List getDescriptions() {
		// TODO Auto-generated method stub
		return this._description;
	}

	public String getFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	public List getFormats() {
		// TODO Auto-generated method stub
		return this._format;
	}

	public String getIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}

	public List getIdentifiers() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLanguage() {
		String language = null;
		if (!this.language.isEmpty()) {
			language = this.language.get(0);
		}
		return language;
	}

	public List getLanguages() {
		return this.language;
	}

	public String getPublisher() {
		// TODO Auto-generated method stub
		return null;
	}

	public List getPublishers() {
		// TODO Auto-generated method stub
		return this._publisher;
	}

	public String getRelation() {
		// TODO Auto-generated method stub
		return null;
	}

	public List getRelations() {
		// TODO Auto-generated method stub
		return this._relation;
	}

	public String getRights() {
		String rights = null;
		if (!this.rights.isEmpty()) {
			rights = this.rights.get(0);
		}
		return rights;
	}

	public List getRightsList() {
		// TODO Auto-generated method stub
		return this.rights;
	}

	public String getSource() {
		// TODO Auto-generated method stub
		return null;
	}

	public List getSources() {
		// TODO Auto-generated method stub
		return this._source;
	}

	public DCSubject getSubject() {
		// TODO Auto-generated method stub
		return null;
	}

	public List getSubjects() {
		
		return this.subjects;
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	public List getTitles() {
		// TODO Auto-generated method stub
		return this.titles;
	}

	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	public List getTypes() {
		// TODO Auto-generated method stub
		return this._type;
	}

	public void setContributor(String contributor) {
		// TODO Auto-generated method stub

	}

	public void setContributors(List contributors) {
		// TODO Auto-generated method stub

	}

	public void setCoverage(String coverage) {
		// TODO Auto-generated method stub

	}

	public void setCoverages(List coverages) {
		// TODO Auto-generated method stub

	}

	public void setCreator(String creator) {
		// TODO Auto-generated method stub

	}

	public void setCreators(List creators) {
		// TODO Auto-generated method stub

	}

	public void setDate(Date date) {
		this.dates.add(date);
	}

	public void setDates(List dates) {
		// TODO Auto-generated method stub

	}

	public void setDescription(String description) {
		// TODO Auto-generated method stub

	}

	public void setDescriptions(List descriptions) {
		// TODO Auto-generated method stub

	}

	public void setFormat(String format) {
		// TODO Auto-generated method stub

	}

	public void setFormats(List formats) {
		// TODO Auto-generated method stub

	}

	public void setIdentifier(String identifier) {
		// TODO Auto-generated method stub

	}

	public void setIdentifiers(List identifiers) {
		// TODO Auto-generated method stub

	}

	public void setLanguage(String language) {
		this.language.add(language);

	}

	public void setLanguages(List languages) {
		this.language = language;

	}

	public void setPublisher(String publisher) {
		// TODO Auto-generated method stub

	}

	public void setPublishers(List publishers) {
		// TODO Auto-generated method stub

	}

	public void setRelation(String relation) {
		// TODO Auto-generated method stub

	}

	public void setRelations(List relations) {
		// TODO Auto-generated method stub

	}

	public void setRights(String rights) {
		this.rights.add(rights);
	}

	public void setRightsList(List<String> rights) {
		this.rights = rights;

	}

	public void setSource(String source) {
		// TODO Auto-generated method stub

	}

	public void setSources(List sources) {
		// TODO Auto-generated method stub

	}

	public void setSubject(DCSubject subject) {
		// TODO Auto-generated method stub

	}

	public void setSubjects(List subjects) {
		this.subjects = subjects;
	}

	public void setTitle(String title) {
		// TODO Auto-generated method stub

	}

	public void setTitles(List titles) {
		// TODO Auto-generated method stub

	}

	public void setType(String type) {
		// TODO Auto-generated method stub

	}

	public void setTypes(List types) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
