package com.sun.syndication.feed.atom;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.sun.syndication.feed.WireFeed;

public class Feed extends WireFeed {
	private  java.util.List 	alternateLinks;
    
	private  java.util.List 	authors;
	           
	private  java.util.List 	categories;
	           
	private  java.util.List 	contributors;
	           
	private  java.util.List 	entries;
	           
	private  Generator 	generator;
	           
	private  java.lang.String 	icon;
	           
	private  java.lang.String 	id;
	           
	private  Content 	info;
	           
	private  java.lang.String 	language;
	           
	private  java.lang.String 	logo;
	           
	private  java.util.List 	modules;
	           
	private  java.util.List 	otherLinks;
	           
	private  java.lang.String 	rights;
	           
	private  Content 	subtitle;
	           
	private  Content 	title;
	           
	private  java.util.Date 	updated;
	           
	private  String 	xmlBase ;
	
	public Feed(){}
    
Feed(java.lang.String type) {
}
public java.util.List getAlternateLinks() {
	return alternateLinks;
}
public void setAlternateLinks(java.util.List alternateLinks) {
	this.alternateLinks = alternateLinks;
}
public java.util.List getAuthors() {
	return authors;
}
public void setAuthors(java.util.List authors) {
	this.authors = authors;
}
public java.util.List getCategories() {
	return categories;
}
public void setCategories(java.util.List categories) {
	this.categories = categories;
}
public java.util.List getContributors() {
	return contributors;
}
public void setContributors(java.util.List contributors) {
	this.contributors = contributors;
}
public java.util.List getEntries() {
	return entries;
}
public void setEntries(java.util.List entries) {
	this.entries = entries;
}
public java.lang.String getIcon() {
	return icon;
}
public void setIcon(java.lang.String icon) {
	this.icon = icon;
}
public java.lang.String getId() {
	return id;
}
public void setId(java.lang.String id) {
	this.id = id;
}
public java.lang.String getLanguage() {
	return language;
}
public void setLanguage(java.lang.String language) {
	this.language = language;
}
public java.lang.String getLogo() {
	return logo;
}
public void setLogo(java.lang.String logo) {
	this.logo = logo;
}
public java.util.List getModules() {
	return modules;
}
public void setModules(java.util.List modules) {
	this.modules = modules;
}
public java.util.List getOtherLinks() {
	return otherLinks;
}
public void setOtherLinks(java.util.List otherLinks) {
	this.otherLinks = otherLinks;
}
public java.lang.String getRights() {
	return rights;
}
public void setRights(java.lang.String rights) {
	this.rights = rights;
}
public Content getSubtitle() {
	return subtitle;
}
public void setSubtitle(Content subtitle) {
	this.subtitle = subtitle;
}
public Content getTitle() {
	return title;
}
public void setTitle(Content title) {
	this.title = title;
}
public java.util.Date getUpdated() {
	return updated;
}
public void setUpdated(java.util.Date updated) {
	this.updated = updated;
}
public String getXmlBase() {
	return xmlBase;
}
public void setXmlBase(String xmlBase) {
	this.xmlBase = xmlBase;
}
public Generator getGenerator() {
	return generator;
}
public void setGenerator(Generator generator) {
	this.generator = generator;
}
public Content getInfo() {
	return info;
}
public void setInfo(Content info) {
	this.info = info;
}
public boolean equals(Object obj){
	return EqualsBuilder.reflectionEquals(this, obj);
}
public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
 }
}