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

import org.apache.log4j.Logger;

public class Author {
    private static final Logger log = Logger.getLogger(Author.class);
    private String name = "";
    private String email = "";
    private String href = "";
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHref() {
        return href;
    }
    public void setHref(String href) {
        this.href = href;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
		sb.append("name: '").append(name).append("', ");
		sb.append("email: '").append(email).append("', ");
		sb.append("url: '").append(href).append('\'');
        sb.append('}');
        return sb.toString();
    }
    
    public String getAuthor() {
        log.debug(this);
        String ret = "";
        if (name != null && !"".equals(email)) {
            ret = name + " (" + email + ')';
        } else if (!"".equals(name)) {
            ret = name;
        } 
        return ret;
    }
    
    public void setAuthor(String content) {
        log.debug(this);
        log.debug("sync_author: " + content);
        content = content.trim();
        // TODO: This should be a regex.
        if (content.contains("(")) {
            String part1 = content.substring(0, content.indexOf('('))
            .trim();
            String part2 = content.substring(content.lastIndexOf('(') + 1,
                    content.lastIndexOf(')')).trim();
            if (part1.contains("@")) {
                name = part2;
                email = part1;
            } else {
                name = part1;
                email = part2;
            }
        } else if (content.contains("<")) {
            String part1 = content.substring(0, content.indexOf('<')).trim();
            String part2 = content.substring(content.lastIndexOf('<') + 1,
                        content.lastIndexOf('>')).trim();
            if (part1.contains("@")) {
                name = part2;
                email = part1;
            } else {
                name = part1;
                email = part2;
            }  
        } else if(name == null || "".equals(name.trim())){
            name = content;
        }
        log.debug(this);
    }
}

