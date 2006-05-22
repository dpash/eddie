package uk.org.catnip.javarss;

import java.io.*;
import java.util.regex.*;
import java.util.Iterator;
import org.apache.log4j.Logger;
import uk.org.catnip.javarss.parser.Parser;
import org.python.util.PythonInterpreter;
import org.python.core.*;
import java.util.Arrays;

public class Test {
    static Logger log = Logger.getLogger(Test.class);

    private PythonInterpreter interp = new PythonInterpreter();

    private int debug = 0;

    public int total_tests = 0;

    public int failed_tests = 0;

    public int passed_tests = 0;

    public void parse_dir(String dirname) {

        File file_or_dir = new File(dirname);
        if (file_or_dir.isFile()) {
            test(dirname);

        } else {
            String[] dir = file_or_dir.list();
            java.util.Arrays.sort(dir);
            for (int i = 0; i < dir.length; i++) {
                File dir_entry = new File(dirname + "/" + dir[i]);
                if (dir_entry.isDirectory()) {
                    System.out.println("descending into " + dir[i]);
                    parse_dir(dirname + "/" + dir[i]);
                    System.out.println();
                } else if (dir_entry.isFile()) {

                    test(dirname + "/" + dir[i]);
                }
            }
        }

    }

    public boolean test(String filename) {
        total_tests++;

        boolean ok = false;

        try {

            BufferedReader in = new BufferedReader(new FileReader(filename));
            String line;
            String description = "";
            String test = "";
            Pattern desc_pattern = Pattern.compile("^Description: (.*)$");
            Pattern test_pattern = Pattern.compile("^Expect: (.*)$");

            while ((line = in.readLine()) != null) {
                Matcher desc_matcher = desc_pattern.matcher(line);
                Matcher test_matcher = test_pattern.matcher(line);
                if (desc_matcher.matches()) {
                    description = desc_matcher.group(1);
                } else if (test_matcher.matches()) {
                    test = test_matcher.group(1);
                }
            }
            in.close();
            if (log.isInfoEnabled()) {
                log.info(filename);
                log.info("Description: " + description);
                log.info("Test: " + test);
            }
            Parser parser = new Parser();
            Feed feed = parser.parse(filename);
            if (log.isInfoEnabled()) {
                log.info(feed);
            }
            runPython(feed, test);
            if (log.isInfoEnabled()) {
                log.info("parse passed");
            } else {
                System.out.print(".");
            }
            ok = true;
            passed_tests++;
        } catch (Exception ex) {
            if (log.isDebugEnabled()) {
                log.debug("parse failed: " + ex);
            } else if (log.isInfoEnabled()){
                log.info("parse failed: " + ex.getCause());
            } else {
                System.out.print("x");
            }
            
            failed_tests++;
        }
        return ok;

    }

    public void runPython(Feed feed, String test) {

        if (feed.error) {
            interp.set("bozo", new PyInteger(1));
        } else {
            interp.set("bozo", new PyInteger(0));
        }
        PyDictionary feed_dict = new PyDictionary();
        Iterator it = feed.keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            feed_dict.__setitem__(key, new PyString(feed.get(key)));
        }

        PyList entries_list = new PyList();
        Iterator entries = feed.entries();
        while (entries.hasNext()) {
            PyDictionary entry_dict = new PyDictionary();
            Entry entry = (Entry) entries.next();
            log.debug("adding " + entry);
            Iterator entry_it = entry.keys();
            while (entry_it.hasNext()) {
                String key = (String) entry_it.next();
                entry_dict.__setitem__(key, new PyString(entry.get(key)));
            }
            if (entry.getAuthor() != null) {
                PyDictionary author_detail = new PyDictionary();
                author_detail.__setitem__("name", new PyString(entry
                        .getAuthor().name));
                author_detail.__setitem__("email", new PyString(entry
                        .getAuthor().email));
                author_detail.__setitem__("url", new PyString(
                        entry.getAuthor().url));
                entry_dict.__setitem__("author_detail", author_detail);
            }
            entries_list.append(entry_dict);
        }

        if (feed.getAuthor() != null) {
            PyDictionary author_detail = new PyDictionary();
            author_detail.__setitem__("name", new PyString(
                    feed.getAuthor().name));
            author_detail.__setitem__("email", new PyString(
                    feed.getAuthor().email));
            author_detail
                    .__setitem__("url", new PyString(feed.getAuthor().url));
            feed_dict.__setitem__("author_detail", author_detail);
        }
        interp.set("feed", feed_dict);
        interp.set("entries", entries_list);
        interp.exec(test);

    }
}
