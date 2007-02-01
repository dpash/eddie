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

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class Entry extends FeedContext {
    private List<Detail> content = new LinkedList<Detail>();
    private List<Enclosure> enclosures = new LinkedList<Enclosure>();
    private Source source;
    private boolean guidIsLink = false;
    public boolean isGuidIsLink() {
        return guidIsLink;
    }

    public void setGuidIsLink(boolean guidIsLink) {
        if (links.isEmpty()) {
        this.guidIsLink = guidIsLink;
        }
    }

    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("{\n");
        Iterator it = property_map.keySet().iterator();
        while (it.hasNext()){
            String key = (String)it.next();
            String value = property_map.get(key);
            ret.append("\t");
            ret.append(key);
            ret.append(" = '");
            if (value.length() > 100) {
                ret.append(value.substring(0,100));
            } else {
                ret.append(value);
            }
            ret.append("',\n");
        }
        // ret.append(property_map.toString());

        if (!content.isEmpty()) {
            ret.append("\tcontent =");
            ret.append(content);
            ret.append(",\n");
        }
        if (this.getTitle() != null) {
            ret.append("\ttitle_detail = '");
            ret.append(this.getTitle());
            ret.append("',\n");
        }
        if (!contributors.isEmpty()) {
            ret.append("\tcontributors = ");
            ret.append(contributors);
            ret.append(",\n");
        }
        if (!links.isEmpty()) {
            ret.append("\tlinks = ");
            ret.append(links);
            ret.append("',\n");
        }
        if (!categories.isEmpty()) {
            ret.append("\tcategories = ");
            ret.append(categories);
            ret.append("',\n");
        }
        if (!enclosures.isEmpty()) {
            ret.append("\tenclosures = ");
            ret.append(enclosures);
            ret.append("',\n");
        }
        if (getCreated() != null) {
            ret.append("\tcreated = '");
            ret.append(getCreated());
            ret.append("'\n");
        }
        if (getIssued() != null) {
            ret.append("\tissued = '");
            ret.append(getIssued());
            ret.append("'\n");
        }
        if (getExpired() != null) {
            ret.append("\texpired = '");
            ret.append(getExpired());
            ret.append("'\n");
        }
        if (getModified() != null) {
            ret.append("\tmodifed = '");
            ret.append(getModified());
            ret.append("'\n");
        }
        ret.append("}");
        return ret.toString();
    }
    
    public void addContent(Detail c) {
        content.add(c);
    }
 
    @Deprecated
    public Iterator contents() {
        return content.iterator();
    }

    public void addEnclosure(Enclosure e) {
        enclosures.add(e);
    }
 
    @Deprecated
    public Iterator enclosures() {
        return enclosures.iterator();
    }
    public boolean hasEnclosures() {
        return !enclosures.isEmpty();
    }
    
    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

	public List<Detail> getContents() {
		return this.content;
	}

	public List<Enclosure> getEnclosures() {
		return this.enclosures;
	}
}
