#!/bin/sh

# Change to the path of the native libraries
export LD_LIBRARY_PATH=/home/achan/Desktop/.developer/Leap/LeapMotionRecorder/

javac -classpath LeapJava.jar ./au/com/bytecode/opencsv/*.java ./leapmotionauthentication/*.java

java -classpath "./LeapJava.jar:." leapmotionauthentication/LeapMotionAuthentication