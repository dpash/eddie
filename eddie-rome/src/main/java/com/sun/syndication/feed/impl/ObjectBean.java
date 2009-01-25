package com.sun.syndication.feed.impl;

import java.io.Serializable;
import java.util.Set;

public class ObjectBean implements Cloneable, Serializable {

	
	private  CloneableBean 	_cloneableBean;
    
	private  EqualsBean 	_equalsBean;
	           
	private  ToStringBean 	_toStringBean;
	
	public ObjectBean(Class beanClass, Object obj){}
    
	public ObjectBean(Class beanClass, Object obj, Set ignoreProperties){}
	public Object 	clone() { return this;}
    
public boolean 	equals(Object other){return true;}
    
public int 	hashCode(){ return 1;}
   
public String 	toString()   { return new String("");}
}
