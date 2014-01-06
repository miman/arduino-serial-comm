package eu.miman.comm.arduino.arduino_serial_comm.impl;

import java.io.IOException;
import java.io.OutputStream;

import eu.miman.comm.arduino.arduino_serial_comm.SerialPortObserver;

/**
 * This is an abstract class for all observers that wants to communicate with characters in contrast to binary communication.
 * 
 * It also adds the possibility to use a separator character to get the data in blocks ending with the separator.
 * 
 * @author Mikael Thorman
 */
public abstract class SerialPortStringObserverImpl implements
		SerialPortObserver {
	String sendBuffer = null;
	OutputStream serialOut = null;

	abstract public void handleData(String data);

	/**
	 * The output stream to the serial port is set here, so the observer can write to the serial connection.
	 * @param serialOut	The serial connection output stream
	 */
	public void setOutputStream(OutputStream serialOut) {
		this.serialOut = serialOut;
	}

	/**
	 * A helper function used to write data to the serial connection.
	 * @param data	The data to send
	 * @throws IOException	If there was an exception writing the data to the serial connection.
	 */
	protected void writeToOutput(String data) throws IOException {
		serialOut.write(data.getBytes());
	}

	/**
	 * This function is called when new data is received over the serial port. 
	 * @param buffer	The received data.
	 * @param length	The length of the received data.
	 */
	public void handleData(byte[] buffer, int length) {
		String data = new String(buffer, 0, length);
		if (!useSeparatorCharacter()) {
			handleData(data);
		} else {
			// We are using separator characters
			if (data.contains(getSeparator())) {
				int separatorIndex = data.indexOf(getSeparator());
				if (data.length() > separatorIndex) {
					// There is data after the separator character -> only send data up until the separator, the rest should be kept in the buffer
					// TODO
				}
				if (sendBuffer != null) {
					handleData(sendBuffer + data);
				} else {
					handleData(data);
				}
				sendBuffer = null;
			} else {
				if (sendBuffer != null) {
					sendBuffer = sendBuffer + data;
				} else {
					sendBuffer = data;
				}
			}
		}
	}

	abstract public boolean useSeparatorCharacter();

	abstract public String getSeparator();
}
