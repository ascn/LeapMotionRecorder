@ECHO off
javac -classpath LeapJava.jar ./au/com/bytecode/opencsv/*.java ./leapmotionauthentication/*.java
java -classpath "LeapJava.jar;." leapmotionauthentication/LeapMotionAuthentication