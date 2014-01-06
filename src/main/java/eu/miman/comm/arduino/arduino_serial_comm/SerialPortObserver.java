package eu.miman.comm.arduino.arduino_serial_comm;

import java.io.OutputStream;

/**
 * This is the interface for an observer listening to data from a serial communication with an Arduino unit.
 * 
 * @author Mikael Thorman
 */
public interface SerialPortObserver {
	/**
	 * This function is called when new data is received over the serial port. 
	 * @param data	The received data.
	 * @param length	The length of the received data.
	 */
	void handleData(byte[] data, int length);
	
	/**
	 * The output stream to the serial port is set here, so the observer can write to the serial connection.
	 * @param serialOut	The serial connection output stream
	 */
	void setOutputStream(OutputStream serialOut);
}
