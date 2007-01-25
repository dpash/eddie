#!/bin/sh

export CLASSPATH="/usr/share/java/xercesImpl.jar:/usr/share/java/log4j-1.2.jar:lib/jython.jar:eddie.jar:eddie-test.jar"
export JAVA_HOME=/usr/lib/jvm/java-1.5.0-sun
$JAVA_HOME/bin/java uk.org.catnip.eddie.parser.Main "$@"

