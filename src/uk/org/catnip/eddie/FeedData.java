/* 
 * Eddie RSS and Atom feed parser
 * Copyright (C) 2006  David Pashley
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 * Linking this library statically or dynamically with other modules is making a
 * combined work based on this library. Thus, the terms and conditions of the GNU
 * General Public License cover the whole combination.
 * 
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules, and
 * to copy and distribute the resulting executable under a liense certified by the
 * Open Source Initative (http://www.opensource.org), provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this
 * exception to your version of the library, but you are not obligated to do so.
 * If you do not wish to do so, delete this exception statement from your version.
 */
package uk.org.catnip.eddie;

import java.util.List;
import java.util.LinkedList;
import java.lang.StringBuilder;
import java.util.Iterator;
import uk.org.catnip.eddie.Detail;

public class FeedData extends FeedContext {
    public boolean error = false;
    private Detail info;
    private Detail subtitle;
    private Generator generator;
    private TextInput textinput;
    private Cloud cloud;
    List<Entry> entries = new LinkedList<Entry>();
    public TextInput getTextinput() {
        return textinput;
    }

    public void setTextinput(TextInput textinput) {
        this.textinput = textinput;
    }

    public Generator getGenerator() {
        return generator;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    public String toString() {
       StringBuilder ret = new StringBuilder();
       //ret.append("bozo: " + error);
       ret.append(property_map.toString());
       if (author != null) {
       ret.append("author = " + author + ", ");
       }
       if (info != null) {
           ret.append("info = " + info + ", ");
           }
       if (image != null) {
           ret.append("image = " + image + ", ");
           }
       if (textinput != null) {
           ret.append("textinput = " + textinput + ", ");
           }
       if (generator != null) {
           ret.append("generator = " + generator + ", ");
       }
       if (subtitle != null) {
           ret.append("subtitle = " + subtitle + ", ");
       }
       if (!contributors.isEmpty()) {
           ret.append("contributors = " + contributors + ", ");
       }
       if (!links.isEmpty()) {
       ret.append("links = " + links + ", ");
       }
       if (getTitle() != null) {
           ret.append("title = " + getTitle() + ", ");
           }
       if (!categories.isEmpty()){
           ret.append("categories = " + categories + ", ");
           }
       if (!entries.isEmpty()) {
       ret.append("entries = " + entries.toString());
       }
       
       return ret.toString();
    }
    
    public void addEntry(Entry entry) {
        entries.add(entry);
    }
    
    @Deprecated
    public Iterator<Entry> entries() {
        return entries.iterator();
    }

    public Detail getInfo() {
        return info;
    }

    public void setInfo(Detail info) {
        this.info = info;
    }

    public Detail getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(Detail subtitle) {
        this.subtitle = subtitle;
    }

    public Cloud getCloud() {
        return cloud;
    }

    public void setCloud(Cloud cloud) {
        this.cloud = cloud;
    }

	public List<Entry> getEntries() {
		return entries;
	}
    
}
