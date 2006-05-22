#!/bin/sh

export CLASSPATH="/home/david/Projects/rome/rome-0.8/rome-0.8.jar:/usr/share/java/jdom.jar:/usr/share/java/xercesImpl.jar:/usr/share/java/log4j-1.2.jar:javarss.jar:/home/david/Projects/rome/jython/jython.jar"
export JAVA_HOME=/usr/lib/j2sdk1.5-sun
$JAVA_HOME/bin/java uk.org.catnip.javarss.Main "$@"

