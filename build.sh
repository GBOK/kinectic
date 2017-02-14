#!/bin/bash
mkdir build-temp \
&& javac -classpath /Applications/Processing.app/Contents/Java/core/library/core.jar -d build-temp  src/kinectic/* \
&& jar -cf build-temp library/kinectic.jar \
&& rm -rf build-temp
