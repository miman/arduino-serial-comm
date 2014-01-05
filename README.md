# Purpose

A base library for communicating with an Arduino over serial communication

# Serial Library installation

For this to work you must first install the OS specific files (ex. DLL for windows) for RXTX.

You can find out how here:

[http://rxtx.qbang.org/wiki/index.php/Main_Page](http://rxtx.qbang.org/wiki/index.php/Main_Page)

If you are using a 64-bit OS, the DLLS to download can be found here:

[http://mfizz.com/oss/rxtx-for-java](http://mfizz.com/oss/rxtx-for-java)


Read the installation guidelines in the downloaded zip, but essentially you do like this:

- Copy RXTXcomm.jar ---> <JAVA_HOME>\jre\lib\ext
- Copy rxtxSerial.dll ---> <JAVA_HOME>\jre\bin
- Copy rxtxParallel.dll ---> <JAVA_HOME>\jre\bin

# Usage

The only thing needed after adding the dependancy to this library is to implement an Observer which is given to the ArduinoCommunicator.

The observer should extend **SerialPortStringObserverImpl** or implement **SerialPortObserver**.

An example application could then look like this:

	public class App 
	{
		public static void main( String[] args ) throws Exception
		{
			ArduinoCommunicator communicator = new ArduinoCommunicatorImpl();
			CapacitiveObserver observer = new CapacitiveObserver();
    	
			communicator.run(observer, args);
		}
	}
