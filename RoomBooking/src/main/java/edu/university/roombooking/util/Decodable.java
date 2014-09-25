package edu.university.roombooking.util;

import java.io.UnsupportedEncodingException;


public interface Decodable<T> {

	/**
	 * Decodes encoding string.
	 * 
	 * @param encodedString
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public abstract T getDecodedInstance(String encodedString) throws UnsupportedEncodingException;

}
