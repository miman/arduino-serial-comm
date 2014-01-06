package eu.miman.comm.arduino.arduino_serial_comm.impl;

import eu.miman.comm.arduino.arduino_serial_comm.SerialPortObserver;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ObservableTwoWaySerialComm {
	SerialPortObserver observer;
	OutputStream serialOut = null;

	public ObservableTwoWaySerialComm(SerialPortObserver observer) {
		super();
		this.observer = observer;
	}

	public void connect(String portName, int baudRate) throws Exception {
		CommPortIdentifier portIdentifier = CommPortIdentifier
				.getPortIdentifier(portName);
		if (portIdentifier.isCurrentlyOwned()) {
			System.out.println("Error: Port is currently in use");
			System.exit(-2);
		} else {
			CommPort commPort = portIdentifier.open(this.getClass().getName(),
					2000);

			if (commPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams(baudRate, SerialPort.DATABITS_8,
						SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

				InputStream in = serialPort.getInputStream();
				serialOut = serialPort.getOutputStream();
				observer.setOutputStream(serialOut);

				(new Thread(new SerialReader(in, observer))).start();

			} else {
				System.out
						.println("Error: Only serial ports are handled by this application.");
			}
		}
	}

	/**
	 * This is a Runner class used to listen to data received over the serial
	 * port and route this to the given observer.
	 */
	public static class SerialReader implements Runnable {
		InputStream in;
		SerialPortObserver observer;

		public SerialReader(InputStream in, SerialPortObserver observer) {
			this.in = in;
			this.observer = observer;
		}

		public void run() {
			byte[] buffer = new byte[1024];
			int len = -1;
			try {
				while ((len = this.in.read(buffer)) > -1) {
					observer.handleData(buffer, len);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Send data to the serial port receiver.
	 * @param data	The data to send
	 */
	public void send(String data) {
		try {
			serialOut.write(data.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send data to the serial port receiver.
	 * @param data	The data to send
	 */
	public void send(byte[] data) {
		try {
			serialOut.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
