#!/bin/bash

CORE="/Applications/Processing.app/Contents/Java/core/library/core.jar"
BIN="bin-temp"

if [[ ! -d "$BIN" ]]; then
    mkdir "$BIN"
fi
javac -classpath "$CORE" -d "$BIN" src/kinectic/*.java
jar -cf library/kinectic.jar "$BIN"
rm -rf "$BIN"