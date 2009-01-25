package com.sun.syndication.feed.module;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SyModuleImpl extends ModuleImpl implements SyModule  {
    private static final Set PERIODS = new HashSet();

    static {
        PERIODS.add(HOURLY );
        PERIODS.add(DAILY  );
        PERIODS.add(WEEKLY );
        PERIODS.add(MONTHLY);
        PERIODS.add(YEARLY );
    }

	SyModuleImpl() {
		super(SyModule.class,URI);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getUri() {
		// TODO Auto-generated method stub
		return null;
	}

	public void copyFrom(Object obj) {
		// TODO Auto-generated method stub

	}

	public Class getInterface() {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getUpdateBase() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getUpdateFrequency() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getUpdatePeriod() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setUpdateBase(Date updateBase) {
		// TODO Auto-generated method stub
		
	}

	public void setUpdateFrequency(int updateFrequency) {
		// TODO Auto-generated method stub
		
	}

	public void setUpdatePeriod(String updatePeriod) {
		// TODO Auto-generated method stub
		
	}

}
