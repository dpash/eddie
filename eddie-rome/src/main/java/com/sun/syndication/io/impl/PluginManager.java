package com.sun.syndication.io.impl;

import java.util.List;
import java.util.Map;

import com.sun.syndication.io.WireFeedGenerator;
import com.sun.syndication.io.WireFeedParser;

public abstract class PluginManager {
	private java.util.List _keys;

	private WireFeedGenerator _parentGenerator;

	private WireFeedParser _parentParser;

	private List _pluginsList;

	private Map _pluginsMap;

	private String[] _propertyValues;

	protected PluginManager(java.lang.String propertyKey) {
	}

	protected PluginManager(java.lang.String propertyKey,
			WireFeedParser parentParser, WireFeedGenerator parentGenerator) {
	}

	private java.lang.Class[] getClasses() {
		Class[] classes =  {this.getClass()};
		return classes;
	}

	protected abstract String getKey(Object obj);

	protected List getKeys() {
		return this._keys;
	}

	protected Object getPlugin(String key) {
		return this._pluginsMap.get(key);
	}

	protected Map getPluginMap() {
		return this._pluginsMap;
	}

	protected java.util.List getPlugins() {
		return this._pluginsList;
	}

	private void loadPlugins() {
	}
}
