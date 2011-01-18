#!/bin/bash

CLASS_PATH=/home/chous/dev/undigester/dist/classes:/home/chous/dev/undigester/lib/java/commons-collections.jar:/home/chous/dev/undigester/lib/java/unittest/versionable-0.3.jar:/home/chous/dev/undigester/lib/java/commons-beanutils.jar:/home/chous/dev/undigester/lib/java/patterns0.3a-11.jar:/home/chous/dev/undigester/lib/java/unittest/junit.jar:/home/chous/dev/undigester/lib/java/unittest/utils0.3a-19.jar:/home/chous/dev/undigester/lib/java/unittest/regexpplugin-0.3.jar:/home/chous/dev/undigester/lib/java/unittest/commons-logging-api.jar

# debug.sh main-class-name vm-address suspend
echo $0 $1 $2 $3 $4 $5 $6

$JAVA_HOME/bin/java -Xnoclassgc -Xverify:none -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=$3,address=$2 -Xnoagent -Djava.compiler=NONE -classpath $CLASS_PATH $1
