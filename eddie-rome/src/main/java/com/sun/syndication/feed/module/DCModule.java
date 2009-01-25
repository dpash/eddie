package com.sun.syndication.feed.module;

import java.util.Date;
import java.util.List;

import com.sun.syndication.feed.CopyFrom;

public interface DCModule extends Module, CopyFrom {
	public static final String 	URI =	"http://purl.org/dc/elements/1.1/";
	 String 	getContributor();

java.util.List 	getContributors();

String 	getCoverage();

List 	getCoverages();

String 	getCreator();

List 	getCreators();

Date 	getDate();

List 	getDates();

String 	getDescription();

List 	getDescriptions();

String 	getFormat();

List 	getFormats();

String 	getIdentifier();

List 	getIdentifiers();

String 	getLanguage();

List 	getLanguages();

String 	getPublisher();

List 	getPublishers();

String 	getRelation();

List 	getRelations();

String 	getRights();

List 	getRightsList();

String 	getSource();

List 	getSources();

DCSubject 	getSubject();

List 	getSubjects();

String 	getTitle();

List 	getTitles();

String 	getType();

List 	getTypes();

void 	setContributor(String contributor);

void 	setContributors(List contributors);

void 	setCoverage(String coverage);

void 	setCoverages(List coverages);

void 	setCreator(String creator);

void 	setCreators(List creators);

void 	setDate(Date date);

void 	setDates(List dates);

void 	setDescription(String description);

void 	setDescriptions(List descriptions);

void 	setFormat(String format);

void 	setFormats(List formats);

void 	setIdentifier(String identifier);

void 	setIdentifiers(List identifiers);

void 	setLanguage(String language);

void 	setLanguages(List languages);

void 	setPublisher(String publisher);

void 	setPublishers(List publishers);

void 	setRelation(String relation);

void 	setRelations(List relations);

void 	setRights(String rights);

void 	setRightsList(List<String> rights);

void 	setSource(String source);

void 	setSources(List sources);

void 	setSubject(DCSubject subject);

void 	setSubjects(List subjects);

void 	setTitle(String title);

void 	setTitles(List titles);
 
void 	setType(String type);

void 	setTypes(List types) ;
}
