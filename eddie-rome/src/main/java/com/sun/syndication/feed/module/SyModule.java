package com.sun.syndication.feed.module;

import java.util.Date;

public interface SyModule extends Module {
	public static final java.lang.String 	URI =	"http://purl.org/rss/1.0/modules/syndication/";
	
	static final String HOURLY ="hourly";

	static final String DAILY = "daily";

	static final String WEEKLY = "weekly";

	static final String MONTHLY = "monthly";

	static final String YEARLY = "yearly";
	Date getUpdateBase();

	int getUpdateFrequency();

	String getUpdatePeriod();

	void setUpdateBase(Date updateBase);

	void setUpdateFrequency(int updateFrequency);

	void setUpdatePeriod(String updatePeriod);
}
