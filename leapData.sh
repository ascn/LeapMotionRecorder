#!/bin/sh

# Change to the path of the native libraries
export LD_LIBRARY_PATH=$PWD

javac -classpath LeapJava.jar ./au/com/bytecode/opencsv/*.java ./leapmotionauthentication/*.java

java -classpath "./LeapJava.jar:." leapmotionauthentication/LeapMotionAuthentication