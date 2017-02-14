#!/bin/bash

CORE="/Applications/Processing.app/Contents/Java/core/library/core.jar"
BIN="bin-temp"

mkdir "$BIN" \
&& javac -classpath "$CORE" -d "$BIN" src/kinectic/*.java \
&& jar -cf "$BIN"/*.java library/kinectic.jar \
&& rm -rf "$BIN"
