Leap Motion Data Recorder
=========================

This project collects physical and gesture data from the Leap Motion device. Data are stored in a comma-separated value file.

Using the Recorder
------------------

Download the `LeapSDK` from (developer.leapmotion.com). Place `LeapJava.jar` in the working directory as well as the appropriate libraries for your operating system (only Linux and OSX is supported at this time). Make sure the directory is in the path:

```
export LD_LIBRARY_PATH=/path/to/libraries
```

In a command line, run `leapData.sh`, which compiles the java files and runs the program, and input a file name to store the data. The file will be created in this directory.