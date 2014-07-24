/**
* Copyright (c) 2014 Digi International Inc.,
* All rights not expressly granted are reserved.
*
* This Source Code Form is subject to the terms of the Mozilla Public
* License, v. 2.0. If a copy of the MPL was not distributed with this file,
* You can obtain one at http://mozilla.org/MPL/2.0/.
*
* Digi International Inc. 11001 Bren Road East, Minnetonka, MN 55343
* =======================================================================
*/
package com.digi.xbee.api.models;

import java.util.HashMap;

/**
 * This class enumerates all the special bytes of the XBee protocol
 * that must be escaped when working on API 2 mode.
 * See {@link com.digi.xbee.OperatingMode.XBeeMode}.
 */
public enum SpecialByte {

	// Enumeration elements
	ESCAPE_BYTE(0x7D),
	HEADER_BYTE (0x7E),
	XON_BYTE(0x11),
	XOFF_BYTE(0x13);
	
	// Variables
	private static final HashMap<Integer, SpecialByte> lookupTable = new HashMap<Integer, SpecialByte>();
	
	static {
		for (SpecialByte sb:values())
			lookupTable.put(sb.getValue(), sb);
	}
	
	private final int value;
	
	/**
	 * Class constructor. Instances a new Special Byte with the given value.
	 * 
	 * @param value Value of the special byte.
	 */
	SpecialByte(int value) {
		this.value = value;
	}
	
	/**
	 * Retrieves the Special Byte value.
	 * 
	 * @return The Special Byte value.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Retrieves the Special Byte associated with the given value.
	 * 
	 * @param value Value to retrieve Special Byte;
	 * @return Special Byte for the given value, null if it does not exist.
	 */
	public static SpecialByte get(int value) {
		return lookupTable.get(value);
	}
	
	/**
	 * Escapes the byte by performing a XOR operation with 0x20 value.
	 * 
	 * @return Escaped byte value.
	 */
	public int escapeByte() {
		return value ^ 0x20;
	}
	
	/**
	 * Checks whether the given byte is special or not.
	 * 
	 * @param byteToCheck Byte to check.
	 * @return True if given byte is special, false otherwise.
	 */
	public static boolean isSpecialByte(int byteToCheck) {
		return get(byteToCheck) != null;
	}
}