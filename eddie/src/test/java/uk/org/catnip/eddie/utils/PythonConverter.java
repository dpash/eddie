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
package uk.org.catnip.eddie.utils;

import java.util.Iterator;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.GregorianCalendar;
import java.util.Date;
import org.apache.log4j.Logger;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.org.catnip.eddie.Author;
import uk.org.catnip.eddie.Category;
import uk.org.catnip.eddie.Cloud;
import uk.org.catnip.eddie.Detail;
import uk.org.catnip.eddie.Enclosure;
import uk.org.catnip.eddie.Entry;
import uk.org.catnip.eddie.FeedData;
import uk.org.catnip.eddie.Generator;
import uk.org.catnip.eddie.Image;
import uk.org.catnip.eddie.Link;
import uk.org.catnip.eddie.Source;
import uk.org.catnip.eddie.TextInput;

import org.python.util.PythonInterpreter;
import org.python.core.*;

public class PythonConverter {
    public class EddieDict extends PyDictionary {

		/**
		 * 
		 */
		private static final long serialVersionUID = 3828710848664039712L;
        Logger log = Logger.getLogger(EddieDict.class);
        private PyObject map(PyObject key) {
            PyString str_key = (PyString) key;
            if (str_key.__cmp__(new PyString("url")) == 0) {
                key = new PyString("href");
            }
            log.debug("key = '"+key+"'");
           return key;
        }
        
        public PyObject __finditem__(PyObject key) {
            return super.__finditem__(map(key));
        }
		/*
        @Override
		public PyObject __findattr__(String arg0) {
			PyObject object = null;
			
			object = super.__finditem__(arg0);
				log.debug("found item: " + object);
			if (object == null){
				object = super.__findattr__(arg0);
				log.debug("found attr: " + object);
			}
			
			return object;
		}*/

		public void __setitem__(PyObject key, PyObject value) {
            super.__setitem__(map(key),value);
        }
        public void __delitem__(PyObject key) {
            super.__delitem__(map(key));
        }
    }
    
    static Logger log = Logger.getLogger(PythonConverter.class);

    @NotNull
    private static PythonInterpreter interp = new PythonInterpreter();

    public int total_tests = 0;

    public int failed_tests = 0;

    public int passed_tests = 0;

    public static boolean runPython(@NotNull FeedData feed, String test) throws Exception {

        if (feed.error) {
            interp.set("bozo", new PyInteger(1));
        } else {
            interp.set("bozo", new PyInteger(0));
        }

        PyList entries_list = new PyList();
        List<Entry> entries = feed.getEntries();
        for (Entry entry: entries) {
            entries_list.append(convertEntry(entry));
        }
        if (feed.get("format") != null) {
        interp.set("version", new PyString(feed.get("format")));
        }
        if (feed.get("encoding") != null) {
            interp.set("encoding", new PyString(feed.get("encoding")));
            }
        interp.set("feed", convertFeed(feed));
        interp.set("entries", entries_list);
        interp.exec("ret ="+ test);
        PyInteger ret = (PyInteger)interp.get("ret");
		return ret.getValue() != 0;

	}
    @NotNull
    public static PyDictionary convertFeed(@NotNull FeedData feed) {
        PyDictionary feed_dict = new PythonConverter().new EddieDict();
        Iterator<String> it = feed.keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            if (key.equals("itunes_block") || key.equals("itunes_explicit")) {
                feed_dict.__setitem__(key, new PyInteger(Integer.parseInt(feed.get(key))));
            } else {
            	log.debug(key + " = "+ feed.get(key));
                feed_dict.__setitem__(key, new PyString(feed.get(key)));
            }
        }
        if (feed.getAuthor() != null) {
            feed_dict.__setitem__("author_detail", convertAuthor(feed.getAuthor()));
        }
        if (feed.getPublisher() != null) {
            feed_dict.__setitem__("publisher_detail", convertAuthor(feed.getPublisher()));
        }
        if (feed.getTitle() != null) {
            feed_dict.__setitem__("title_detail",convertDetail(feed.getTitle()));
        }
        if (feed.getSubtitle() != null) {
            feed_dict.__setitem__("subtitle_detail",convertDetail(feed.getSubtitle()));
            feed_dict.__setitem__("tagline_detail",convertDetail(feed.getSubtitle()));
        }
        if (feed.getInfo() != null) {
            feed_dict.__setitem__("info_detail",convertDetail(feed.getInfo()));
        }
        if (feed.getGenerator() != null) {
            feed_dict.__setitem__("generator_detail",convertGenerator(feed.getGenerator()));
        }
        if (feed.getCopyright() != null) {
            feed_dict.__setitem__("copyright_detail",convertDetail(feed.getCopyright()));
            feed_dict.__setitem__("rights_detail",convertDetail(feed.getCopyright()));
        }
        if (feed.getCreated() != null) {
            feed_dict.__setitem__("created_parsed",convertDate(feed.getCreated()));
        }
        if (feed.getIssued() != null) {
            feed_dict.__setitem__("issued_parsed",convertDate(feed.getIssued()));
        }
        if (feed.getModified() != null) {
            feed_dict.__setitem__("modified_parsed",convertDate(feed.getModified()));
            feed_dict.__setitem__("date_parsed",convertDate(feed.getModified()));
        }
        if (feed.getImage() != null) {
            feed_dict.__setitem__("image",convertImage(feed.getImage()));
        }
        if (feed.getTextinput() != null) {
            feed_dict.__setitem__("textinput",convertTextInput(feed.getTextinput()));
        }
        if (feed.getCloud() != null) {
            feed_dict.__setitem__("cloud",convertCloud(feed.getCloud()));
        }
        PyList contributors_list = new PyList();
        for (Author contributor: feed.getContributors()) {
            contributors_list.append(convertAuthor(contributor));
        }
        feed_dict.__setitem__("contributors",contributors_list);
        
        
        PyList links_list = new PyList();
        for (Link link: feed.getLinks()) {
            links_list.append(convertLink(link));
        }
        feed_dict.__setitem__("links",links_list);
        
        PyList category_list = new PyList();
        for (Category category: feed.getCategories()) {
            category_list.append(convertCategoryTuple(category));
        }
        feed_dict.__setitem__("categories",category_list);
      
        // Dict style tags
        category_list = new PyList();
        for (Category category: feed.getCategories()) {
            category_list.append(convertCategory(category));
        }
        feed_dict.__setitem__("tags",category_list);
        
        log.debug(feed_dict);
        
        return feed_dict;
        
        
        
    }
    @NotNull
    public static PyDictionary convertAuthor(@NotNull Author author) {
        PyDictionary author_detail = new PyDictionary();
        author_detail.__setitem__("name", new PyString(author.getName()));
        author_detail.__setitem__("email", new PyString(author.getEmail()));
        author_detail.__setitem__("url", new PyString(author.getHref()));
        return author_detail;
    }
    

    
    
    @NotNull
    public static PyDictionary convertEntry(@NotNull Entry entry) {
        PyDictionary entry_dict = new PyDictionary();
        Iterator<String> entry_it = entry.keys();
        while (entry_it.hasNext()) {
            String key = entry_it.next();
            if (key.equals("itunes_block") || key.equals("itunes_explicit")) {
                entry_dict.__setitem__(key, new PyInteger(Integer.parseInt(entry.get(key))));
            } else {
                entry_dict.__setitem__(key, new PyString(entry.get(key)));
            }
        }
        if (entry.getAuthor() != null) {
            entry_dict.__setitem__("author_detail", convertAuthor(entry.getAuthor()));
        }
        if (entry.getPublisher() != null) {
            entry_dict.__setitem__("publisher_detail", convertAuthor(entry.getPublisher()));
        }
        if (entry.getTitle() != null) {
            entry_dict.__setitem__("title_detail",convertDetail(entry.getTitle()));
        }
        if (entry.getSummary() != null) {
            entry_dict.__setitem__("summary_detail",convertDetail(entry.getSummary()));
        }
        if (entry.getCreated() != null) {
            entry_dict.__setitem__("created_parsed",convertDate(entry.getCreated()));
        }
        if (entry.getIssued() != null) {
            entry_dict.__setitem__("issued_parsed",convertDate(entry.getIssued()));
        }
        if (entry.getExpired() != null) {
            entry_dict.__setitem__("expired_parsed",convertDate(entry.getExpired()));
        }
        if (entry.getCopyright() != null) {
            entry_dict.__setitem__("rights_detail",convertDetail(entry.getCopyright()));
        }
        if (entry.getSource() != null) {
            entry_dict.__setitem__("source",convertSource(entry.getSource()));
        }
        if (entry.getModified() != null) {
            entry_dict.__setitem__("modified_parsed",convertDate(entry.getModified()));
            entry_dict.__setitem__("date_parsed",convertDate(entry.getModified()));
        }
        if (entry.getImage() != null) {
            entry_dict.__setitem__("image",convertImage(entry.getImage()));
        }
        PyList contents_list = new PyList();
        for (Detail content: entry.getContents()) {
            contents_list.append(convertDetail(content));
        }
        entry_dict.__setitem__("content",contents_list);
        
        PyList contributors_list = new PyList();
        for (Author contributor: entry.getContributors()) {
            contributors_list.append(convertAuthor(contributor));
        }
        entry_dict.__setitem__("contributors",contributors_list);
        
        PyList links_list = new PyList();
        for (Link link: entry.getLinks()) {
            links_list.append(convertLink(link));
        }
        entry_dict.__setitem__("links",links_list);
        
        // Tuple style categories
        PyList category_list = new PyList();
        for (Category category: entry.getCategories()) {
            category_list.append(convertCategoryTuple(category));
        }
        entry_dict.__setitem__("categories",category_list);
        
        // Dict style tags
        category_list = new PyList();
        for (Category category: entry.getCategories()) {
            category_list.append(convertCategory(category));
        }
        entry_dict.__setitem__("tags",category_list);
        
        
        
        PyList enclosure_list = new PyList();
        for (Enclosure enclosure: entry.getEnclosures()) {
            enclosure_list.append(convertEnclosure(enclosure));
        }
        entry_dict.__setitem__("enclosures",enclosure_list);
        
        if (entry.isGuidIsLink()) {
            entry_dict.__setitem__("guidislink", new PyInteger(1));
        } else {
            entry_dict.__setitem__("guidislink", new PyInteger(0));
        }
        
        //log.debug(entry_dict);
        
        return entry_dict;
    }

    @NotNull
    public static PyDictionary convertSource(@NotNull Source source) {
        PyDictionary source_dict = new PyDictionary();
        Iterator<String> source_it = source.keys();
        while (source_it.hasNext()) {
            String key = source_it.next();
            source_dict.__setitem__(key, new PyString(source.get(key)));
        }
        if (source.getAuthor() != null) {
            source_dict.__setitem__("author_detail", convertAuthor(source.getAuthor()));
        }
        if (source.getGenerator() != null) {
            source_dict.__setitem__("generator_detail",convertGenerator(source.getGenerator()));
        }
        if (source.getPublisher() != null) {
            source_dict.__setitem__("publisher_detail", convertAuthor(source.getPublisher()));
        }
        if (source.getSubtitle() != null) {
            source_dict.__setitem__("subtitle_detail",convertDetail(source.getSubtitle()));
        }
        if (source.getTitle() != null) {
            source_dict.__setitem__("title_detail",convertDetail(source.getTitle()));
        }
        if (source.getSummary() != null) {
            source_dict.__setitem__("summary_detail",convertDetail(source.getSummary()));
        }
        if (source.getCreated() != null) {
            source_dict.__setitem__("created_parsed",convertDate(source.getCreated()));
        }
        if (source.getIssued() != null) {
            source_dict.__setitem__("issued_parsed",convertDate(source.getIssued()));
        }
        if (source.getCopyright() != null) {
            source_dict.__setitem__("rights_detail",convertDetail(source.getCopyright()));
        }

        if (source.getModified() != null) {
            source_dict.__setitem__("modified_parsed",convertDate(source.getModified()));
            source_dict.__setitem__("date_parsed",convertDate(source.getModified()));
        }
        
        PyList contributors_list = new PyList();
        for (Author contributor: source.getContributors()) {
            contributors_list.append(convertAuthor(contributor));
        }
        source_dict.__setitem__("contributors",contributors_list);
        
        PyList links_list = new PyList();
        for (Link link: source.getLinks()) {
            links_list.append(convertLink(link));
        }
        source_dict.__setitem__("links",links_list);
        
        // Tuple style categories
        PyList category_list = new PyList();
        for (Category category: source.getCategories()) {
            category_list.append(convertCategoryTuple(category));
        }
        source_dict.__setitem__("categories",category_list);
        
        // Dict style tags
        category_list = new PyList();
        for (Category category: source.getCategories()) {
            category_list.append(convertCategory(category));
        }
        source_dict.__setitem__("tags",category_list);
              
     
        log.debug(source_dict);
        
        return source_dict;
    }
    
    @NotNull
    public static PyDictionary convertDetail(@Nullable Detail detail) {

        PyDictionary detail_dict = new PyDictionary();
        if (detail != null) {
            if (detail.getLanguage() != null) {
                detail_dict.__setitem__("language", new PyString(detail
                        .getLanguage()));
                if (detail.getLanguage().equals("")) {
                    detail_dict.__setitem__("language", Py.None);
                }
            }
            if (detail.getType() != null) {
                detail_dict.__setitem__("type", new PyString(detail.getType()));
            }
            if (detail.getValue() != null) {
                detail_dict.__setitem__("value",
                        new PyString(detail.getValue()));
            }
            if (detail.getSrc() != null) {
                detail_dict.__setitem__("src",
                        new PyString(detail.getSrc()));
            }
            if (detail.getBase() != null) {
                detail_dict.__setitem__("base",
                        new PyString(detail.getBase()));
            }
        }
        return detail_dict;
    }
    @NotNull
    public static PyDictionary convertLink(@NotNull Link link) {
        PyDictionary link_dict =  convertDetail(link);
        if (link.getHref() != null) {
        link_dict.__setitem__("href", new PyString(link.getHref()));
        }
        if (link.getTitle() != null) {
        link_dict.__setitem__("title", new PyString(link.getTitle()));
        }
        if (link.getRel() != null) {
        link_dict.__setitem__("rel", new PyString(link.getRel()));
        }
        if (link.getHreflang() != null) {
            link_dict.__setitem__("hreflang", new PyString(link.getHreflang()));
            }
        if (link.getLength() != null) {
            link_dict.__setitem__("length", new PyString(link.getLength()));
            }
        return link_dict;
    }
    @NotNull
    public static PyDictionary convertGenerator(@NotNull Generator generator) {
        PyDictionary link_dict = convertDetail(generator);
    
    if (generator.getName() != null) {
        link_dict.__setitem__("name", new PyString(generator.getName()));
    }
    if (generator.getUrl() != null) {
        link_dict.__setitem__("url", new PyString(generator.getUrl()));
        link_dict.__setitem__("href", new PyString(generator.getUrl()));
    }
    if (generator.getVersion() != null) {
        link_dict.__setitem__("version", new PyString(generator.getVersion()));
    }
        return link_dict;
    }
    public static  PyTuple convertDate(@Nullable Date date) {
        PyTuple date_tuple;
    
    if (date != null) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        PyObject [] fields = {
                new PyInteger(cal.get(Calendar.YEAR)),
                new PyInteger(cal.get(Calendar.MONTH)+1),
                new PyInteger(cal.get(Calendar.DAY_OF_MONTH)),
                new PyInteger(cal.get(Calendar.HOUR_OF_DAY)),
                new PyInteger(cal.get(Calendar.MINUTE)),
                new PyInteger(cal.get(Calendar.SECOND)),
                new PyInteger(normaliseDayOfWeek(cal.get(Calendar.DAY_OF_WEEK))),
                new PyInteger(cal.get(Calendar.DAY_OF_YEAR)),
                new PyInteger(0)
        };
        date_tuple = new PyTuple(fields);

    } else {
        date_tuple = new PyTuple();
    }

        return date_tuple;
    }
    @NotNull
    public static PyDictionary convertCategory(@NotNull Category category) {
        PyDictionary tags_dict = new PyDictionary();

        if (category.getTerm() != null) {
            tags_dict.__setitem__("term", new PyString(category.getTerm()));
        }
        if (category.getSchedule() != null) {
            tags_dict.__setitem__("scheme", new PyString(category.getSchedule()));
        }
        if (category.getLabel() != null) {
            tags_dict.__setitem__("label", new PyString(category.getLabel()));
        }

        return tags_dict;

    }
    @NotNull
    public static PyTuple convertCategoryTuple(@NotNull Category category) {
        String term = category.getTerm();
        String schedule = category.getSchedule();
        String label = category.getLabel();
        
        if (term == null) { term = ""; }
        if (schedule == null) { schedule = ""; }
        if (label == null) { label = ""; }
        
        PyObject[] fields = { new PyString(schedule),
                new PyString(term),
                new PyString(label) };
        return new PyTuple(fields);

    }
    
    @NotNull
    public static PyDictionary convertImage(@NotNull Image image) {
        PyDictionary image_dict = new PyDictionary();

        if (image.getTitle() != null) {
            image_dict.__setitem__("title", new PyString(image.getTitle()));
        }
        if (image.getUrl() != null) {
            image_dict.__setitem__("url", new PyString(image.getUrl()));
            image_dict.__setitem__("href", new PyString(image.getUrl()));
        }
        if (image.getLink() != null) {
            image_dict.__setitem__("link", new PyString(image.getLink()));
        }
        if (image.getWidth() != null) {
            image_dict.__setitem__("width", new PyInteger(Integer.parseInt(image.getWidth())));
        }
        if (image.getHeight() != null) {
            image_dict.__setitem__("height", new PyInteger(Integer.parseInt(image.getHeight())));
        }
        if (image.getDescription() != null) {
            image_dict.__setitem__("description", new PyString(image
                    .getDescription()));
        }

        return image_dict;
    }
    @NotNull
    public static PyDictionary convertTextInput(@NotNull TextInput textinput) {
        PyDictionary textinput_dict = new PyDictionary();

        if (textinput.getTitle() != null) {
            textinput_dict.__setitem__("title", new PyString(textinput.getTitle()));
        }
        if (textinput.getLink() != null) {
            textinput_dict.__setitem__("link", new PyString(textinput.getLink()));
        }
        if (textinput.getName() != null) {
            textinput_dict.__setitem__("name", new PyString(textinput.getName()));
        }
        if (textinput.getDescription() != null) {
            textinput_dict.__setitem__("description", new PyString(textinput
                    .getDescription()));
        }

        return textinput_dict;
    }
    
    @NotNull
    public static PyDictionary convertEnclosure(@NotNull Enclosure enclosure) {
        PyDictionary enclosure_dict = new PyDictionary();

        if (enclosure.getUrl() != null) {
            enclosure_dict.__setitem__("url", new PyString(enclosure.getUrl()));
            enclosure_dict.__setitem__("href", new PyString(enclosure.getUrl()));
        }
        if (enclosure.getLength() != null) {
            enclosure_dict.__setitem__("length", new PyString(enclosure.getLength()));
        }
        if (enclosure.getType() != null) {
            enclosure_dict.__setitem__("type", new PyString(enclosure.getType()));
        }

        return enclosure_dict;
    }
    @NotNull
    public static PyDictionary convertCloud(@NotNull Cloud cloud) {
        PyDictionary cloud_dict = new PyDictionary();

        if (cloud.getDomain() != null) {
            cloud_dict.__setitem__("domain", new PyString(cloud.getDomain()));
        }
        if (cloud.getPort() != null) {
            cloud_dict.__setitem__("port", new PyString(cloud.getPort()));
        }
        if (cloud.getPath() != null) {
            cloud_dict.__setitem__("path", new PyString(cloud.getPath()));
        }
        if (cloud.getRegisterProcedure() != null) {
            cloud_dict.__setitem__("registerprocedure", new PyString(cloud.getRegisterProcedure()));
        }
        if (cloud.getProtocol() != null) {
            cloud_dict.__setitem__("protocol", new PyString(cloud.getProtocol()));
        }


        return cloud_dict;
    }
    static public int normaliseDayOfWeek(int day) {
        if (day == Calendar.MONDAY) { return 0; }
        if (day == Calendar.TUESDAY) { return 1; }
        if (day == Calendar.WEDNESDAY) { return 2; }
        if (day == Calendar.THURSDAY) { return 3; }
        if (day == Calendar.FRIDAY) { return 4; }
        if (day == Calendar.SATURDAY) { return 5; }
        if (day == Calendar.SUNDAY) { return 6; }
        return 0;
    }
  
}
