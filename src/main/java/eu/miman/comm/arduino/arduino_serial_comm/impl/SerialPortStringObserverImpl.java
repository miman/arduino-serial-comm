package eu.miman.comm.arduino.arduino_serial_comm.impl;

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

	abstract public void handleData(String data);

	public boolean useBinaryData() {
		return false;
	}

	abstract public boolean useSeparatorCharacter();

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

	abstract public String getSeparator();

}
