package eu.miman.comm.arduino.arduino_serial_comm.impl;

import eu.miman.comm.arduino.arduino_serial_comm.ArduinoCommunicator;
import eu.miman.comm.arduino.arduino_serial_comm.SerialPortObserver;

/**
 * This class is used to communicate with an Arduino over serial communication.
 */
public class ArduinoCommunicatorImpl implements ArduinoCommunicator 
{
	/* (non-Javadoc)
	 * @see eu.miman.comm.arduino.arduino_serial_comm.impl.ArduinoCommunicator#run(eu.miman.comm.arduino.arduino_serial_comm.SerialPortObserver, java.lang.String[])
	 */
    public void run( SerialPortObserver observer, String[] args ) throws Exception
    {
    	int baudrate = getBaudRate(args);
    	String comPort = getComPort(args);
        System.out.println( "Starting with (bauderate = " + baudrate + ", comPort = " + comPort +")" );
        ObservableTwoWaySerialComm comm = new ObservableTwoWaySerialComm(observer);
        comm.connect(comPort, baudrate);
    }

    /**
     * Extracts the com port value from the given arguments, will return default COM6 if no argument is given
     * @param args The arguments given to the application when it is started.
     * @return	the com port value from the given arguments, will return default COM6 if no argument is given
     */
	private static String getComPort(String[] args) {
		for (int i = 0; i < args.length; i++) {
			if ("-com".compareToIgnoreCase(args[i]) == 0) {
				return args[i+1];
			}
		}
		return "COM6";	// Default value
	}

	/**
     * Extracts the baud rate value from the given arguments, will return default 9600 if no argument is given
     * @param args The arguments given to the application when it is started.
     * @return	the baud rate value from the given arguments, will return default 9600 if no argument is given
	 */
	private static int getBaudRate(String[] args) {
		for (int i = 0; i < args.length; i++) {
			if ("-baud".compareToIgnoreCase(args[i]) == 0) {
				try {
					return Integer.parseInt(args[i+1]);
				} catch (NumberFormatException e) {
					System.out.println("The given baude rate has invalid format '" + args[i+1] + "'");
					System.exit(-1);
				}
			}
		}
		return 9600;	// Default baud rate
	}
}
