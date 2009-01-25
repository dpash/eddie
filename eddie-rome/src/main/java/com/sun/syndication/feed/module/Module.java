package com.sun.syndication.feed.module;

import com.sun.syndication.feed.CopyFrom;

public interface Module extends Cloneable, CopyFrom, java.io.Serializable {
	Object clone();

	String getUri();
}
