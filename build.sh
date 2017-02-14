#!/bin/bash

CORE="/Applications/Processing.app/Contents/Java/core/library/core.jar"
BIN="bin-temp"

echo "Compiling library..."

if [[ ! -d "$BIN" ]]; then
    mkdir "$BIN"
fi
javac -classpath "$CORE" -d "$BIN" src/okgb/kinectic/*.java
jar -cf library/kinectic.jar -C "$BIN" .
#rm -rf "$BIN"

echo "Done."