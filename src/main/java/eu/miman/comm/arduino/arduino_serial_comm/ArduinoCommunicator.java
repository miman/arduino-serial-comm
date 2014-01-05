package eu.miman.comm.arduino.arduino_serial_comm;


/**
 * An interface for a class used to communicate with an Arduino over serial communication.
 */
public interface ArduinoCommunicator {

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
	public void run(SerialPortObserver observer, String[] args)
			throws Exception;

}