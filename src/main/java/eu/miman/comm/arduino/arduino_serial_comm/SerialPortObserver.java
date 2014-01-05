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
	void handleData(String data);

	/**
	 * Returns if this observer wants to receive the data in binary format (as opposed to as String format).
	 * @return	true if this observer wants the data as binary data.
	 */
	boolean useBinaryData();
	
	/**
	 * Returns if this observer wants to receive the data in blocks separated by a separator character or not.
	 * @return	true if this observer wants the the data in blocks separated by a separator character.
	 */
	boolean useSeparatorCharacter();

	/**
	 * Returns the separator string, the observer wants to have data in chunks separated by this string.
	 * @return	the separator string
	 */
	String getSeparator();
}
