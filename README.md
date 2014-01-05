A base library for communicating with an Arduino over serial communication

For this to work you must first install the OS specific files (ex. DLL for windows) for RXTX.

You can find out how here:

[http://rxtx.qbang.org/wiki/index.php/Main_Page](http://rxtx.qbang.org/wiki/index.php/Main_Page)

If you are using a 64-bit OS, the DLLS to download can be found here:

[http://mfizz.com/oss/rxtx-for-java](http://mfizz.com/oss/rxtx-for-java)


Read the installation guidelines in the downloaded zip, but essentially you do like this:

- Copy RXTXcomm.jar ---> <JAVA_HOME>\jre\lib\ext
- Copy rxtxSerial.dll ---> <JAVA_HOME>\jre\bin
- Copy rxtxParallel.dll ---> <JAVA_HOME>\jre\bin
