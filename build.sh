#!/bin/bash
javac -classpath /Applications/Processing.app/Contents/Java/core/library/core.jar -d . src/*.java \
&& jar -cf *.class library/kinectic.jar \
&& rm *.class
