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
        ret.append(property_map.toString());
        if (!content.isEmpty()) {
        ret.append("content =" + content + ", ");
        }
        if (this.getTitle() != null) {
        ret.append("title_detail = '" + this.getTitle() + "', ");
        }
        if (!contributors.isEmpty()) {
        ret.append("contributors = " + contributors + ", ");
        }
        if (!links.isEmpty()){
        ret.append("links = " + links);
        }
        if (!categories.isEmpty()){
            ret.append("categories = " + categories);
            }
        if (getCreated() != null){
            ret.append("created = " + getCreated());
            }
        if (getIssued() != null){
            ret.append("issued = " + getIssued());
            }
        if (getModified() != null){
            ret.append("modifed = " + getModified());
            }
        return ret.toString();
     }
    
    public void addContent(Detail c) {
        content.add(c);
    }
 
    
    public Iterator contents() {
        return content.iterator();
    }

    public void addEnclosure(Enclosure e) {
        enclosures.add(e);
    }
 
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
}
