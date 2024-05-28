package com.digi.xbee.api.connection.serial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.digi.xbee.api.exceptions.InterfaceInUseException;
import com.digi.xbee.api.exceptions.InvalidConfigurationException;
import com.digi.xbee.api.exceptions.InvalidInterfaceException;
import com.digi.xbee.api.exceptions.PermissionDeniedException;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortInvalidPortException;

/**
 * Implementation of AbstractSerialPort using JSerialCom
 * @author dg50
 *
 */
public class SerialPortJSC extends AbstractSerialPort implements SerialPortDataListener {

	private SerialPort serialPort = null;
	private InputStream inputStream;
	private OutputStream outputStream;
	
	public SerialPortJSC(String port, SerialPortParameters parameters) {
		this(port, parameters, DEFAULT_PORT_TIMEOUT);
	}

	public SerialPortJSC(String port, int baudRate) {
		this(port, baudRate, DEFAULT_PORT_TIMEOUT);
	}

	public SerialPortJSC(String port, int baudRate, int receiveTimeout) {
		super(port, baudRate, receiveTimeout);
	}

	public SerialPortJSC(String port, SerialPortParameters parameters, int receiveTimeout) {
		super(port, parameters, receiveTimeout);
	}

	@Override
	public void open() throws InterfaceInUseException, InvalidInterfaceException, InvalidConfigurationException,
	PermissionDeniedException {
		try {
			serialPort = SerialPort.getCommPort(getPort());
		}
		catch (SerialPortInvalidPortException spe) {
			throw new InvalidInterfaceException(spe.getLocalizedMessage());
		}
		if (parameters == null) {
			parameters = new SerialPortParameters(baudRate, DEFAULT_DATA_BITS, DEFAULT_STOP_BITS, DEFAULT_PARITY, DEFAULT_FLOW_CONTROL);
		}
		serialPort.setBaudRate(parameters.baudrate);
		serialPort.setFlowControl(parameters.flowControl);
		serialPort.setNumDataBits(parameters.dataBits);
		serialPort.setNumStopBits(parameters.stopBits);
		serialPort.setParity(parameters.parity);
		serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, receiveTimeout*1000, 0);
		connectionOpen = serialPort.openPort();
		inputStream = serialPort.getInputStream();
		outputStream = serialPort.getOutputStream();
		serialPort.addDataListener(this);

	}

	@Override
	public void close() {
		if (serialPort == null) {
			return;
		}
		serialPort.closePort();
		serialPort = null;
	}

	@Override
	public InputStream getInputStream() {
		return inputStream;
	}

	@Override
	public OutputStream getOutputStream() {
		return outputStream;
	}

	@Override
	public void setDTR(boolean state) {
		if (serialPort == null) {
			return;
		}
		if (state) {
			serialPort.setDTR();
		}
		else {
			serialPort.clearDTR();
		}
	}

	@Override
	public void setRTS(boolean state) {
		if (serialPort == null) {
			return;
		}
		if (state) {
			serialPort.setRTS();
		}
		else {
			serialPort.clearRTS();
		}

	}

	@Override
	public boolean isCTS() {
		if (serialPort == null) {
			return false;
		}
		return serialPort.getCTS();
	}

	@Override
	public boolean isDSR() {
		if (serialPort == null) {
			return false;
		}
		return serialPort.getDSR();
	}

	@Override
	public boolean isCD() {
		if (serialPort == null) {
			return false;
		}
		return serialPort.getDCD();
	}

	@Override
	public void setBreak(boolean enabled) {
		if (serialPort == null) {
			return;
		}
		if (enabled) {
			serialPort.setBreak();
		}
		else {
			serialPort.clearBreak();
		}
	}

	@Override
	public void sendBreak(int duration) {
		if (serialPort == null) {
			return;
		}
//		serialPort.
	}

	@Override
	public void setReadTimeout(int timeout) {
		if (serialPort == null) {
			return;
		}
		serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, timeout, serialPort.getWriteTimeout());
	}

	@Override
	public int getReadTimeout() {
		if (serialPort == null) {
			return 0;
		}
		return serialPort.getReadTimeout();
	}

	@Override
	public int getListeningEvents() {
		// TODO Auto-generated method stub
		return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
//		System.out.println("Serial port event: " + event);
		int avail = serialPort.bytesAvailable();
		if (avail > 0) {
			synchronized (this) {
				this.notify();
			}
		}
//		byte[] data = new byte[avail];
//		try {
//			inputStream.read(data);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////		byte[] data = event.getReceivedData();
//		String str = new String(data);
//		System.out.println(str);
	}

}
