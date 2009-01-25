package com.sun.syndication.feed.module;

public interface Extendable {
	Module getModule(java.lang.String uri);

	java.util.List getModules();

	void setModules(java.util.List modules);

}
