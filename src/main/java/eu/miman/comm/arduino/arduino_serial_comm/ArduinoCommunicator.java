package eu.miman.comm.arduino.arduino_serial_comm;

import eu.miman.comm.arduino.arduino_serial_comm.impl.ObservableTwoWaySerialComm;

/**
 * This class is used to communicate with an Arduino over serial communication.
 *
 */
public class ArduinoCommunicator 
{
	/**
	 * This function activates the communication based on the arguments to the application and sends the received data to the given observer.
	 * 
	 * 2 arguments can be given to this application
	 * 
	 * - Com port to connect through, ex: "-com COM6" will connect to com port COM6
	 * 
	 * - Baud rate to use, ex: "-baud 9600" will make the app use 9600
	 *  
	 * @param observer	The observer that wants to have the received serial communication.
	 * @param args	The arguments given to the application when it is started.
	 * @throws Exception	Any exception thrown
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
