package com.sun.syndication.feed.synd.impl;

import java.util.List;
import java.util.Vector;

import com.sun.syndication.feed.synd.Converter;
import com.sun.syndication.io.impl.PluginManager;

public class Converters extends PluginManager {
	public static final String CONVERTERS_KEY = "Converters.class";

	@Override
	protected String getKey(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public Converters() {
		super(CONVERTERS_KEY);
	}

	public Converter getConverter(java.lang.String feedType) {
		return null;
	}

	public List getSupportedFeedTypes() {
		return new Vector();
	}
}
