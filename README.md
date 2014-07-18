LeapMotionRecorder
=========================

This project collects physical and gesture data from the Leap Motion device. Data are stored in a comma-separated value file. For operating system control, check out [LeapControl](https://github.com/alexanderchan97/LeapControl).

##<a name="#usage"></a>Using the Recorder
Download the `LeapSDK` from (developer.leapmotion.com). Place `LeapJava.jar` in the working directory as well as the appropriate libraries for your operating system.

In a command line, run `leapData.sh` or `leapData.bar`, which compiles the java files and runs the program, and input a file name to store the data. Include the file extension (`.csv`).