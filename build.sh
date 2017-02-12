#!/bin/bash
javac -classpath /Applications/Processing.app/Contents/Java/core/library/core.jar -d . *.java && jar -cf library/kinectic.jar DepthProcessor
