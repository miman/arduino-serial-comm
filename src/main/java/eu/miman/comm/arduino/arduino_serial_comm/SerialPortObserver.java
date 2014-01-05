package eu.miman.comm.arduino.arduino_serial_comm;

/**
 * This is the interface for an observer listening to data from a serial communication with an Arduino unit.
 * 
 * @author Mikael Thorman
 */
public interface SerialPortObserver {
	/**
	 * This function is called when new data is received over the serial port. 
	 * @param data	The received data.
	 */
	void handleData(byte[] data, int length);
}
