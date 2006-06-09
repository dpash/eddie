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

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import uk.org.catnip.eddie.Link;
import java.util.Date;

import org.apache.log4j.Logger;
public class FeedContext {
    static Logger log = Logger.getLogger(FeedContext.class);
    protected Hashtable<String, String> property_map = new Hashtable<String, String>();
    protected Author author;
    protected Author publisher;
    private Detail title;
    private Date issued;
    private Date modified;
    private Date created;
    protected List<Author> contributors = new LinkedList<Author>();
    protected List<Link> links = new LinkedList<Link>();
    protected List<Category> categories = new LinkedList<Category>();
    private Detail summary;
    private Detail copyright;
    protected Image image;
    public String set(String key, String value) {
        property_map.put(key,value);
        return value;
    }
    public String get(String key){
        return (String)property_map.get(key);
    }
    public boolean has(String key) {
        return property_map.containsKey(key);
    }
    public Iterator keys() {
        return property_map.keySet().iterator();
    }
    
    public Hashtable getHashtable() {
        return property_map;
    }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    
    public void addContributor(Author a) {
        contributors.add(a);
    }
    public void addLink(Link link) {
        log.trace("adding link: " + link);
        links.add(link);
    }
    public void addCategory(Category category) {
        Iterator it = categories.iterator();
        while (it.hasNext()) {
            Category cur_cat = (Category)it.next();
            if (category.getTerm().equals(cur_cat.getTerm())) {
                return;
            }
        }
        categories.add(category);
    }
    public Iterator contributors() {
        return contributors.iterator();
    }
    public Iterator links() {
        return links.iterator();
    }
    public Iterator categories() {
        return categories.iterator();
    }
    public Detail getTitle() {
        return title;
    }

    public void setTitle(Detail title_detail) {
        this.title = title_detail;
    }
    public Detail getSummary() {
        return summary;
    }
    public void setSummary(Detail summary) {
        this.summary = summary;
    }
    public Date getIssued() {
        return issued;
    }
    public void setIssued(Date issued) {
        this.issued = issued;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public Date getModified() {
        return modified;
    }
    public void setModified(Date modified) {
        this.modified = modified;
    }
    public Author getPublisher() {
        return publisher;
    }
    public void setPublisher(Author publisher) {
        this.publisher = publisher;
    }
    public Detail getCopyright() {
        return copyright;
    }
    public void setCopyright(Detail copyright) {
        this.copyright = copyright;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }

}
