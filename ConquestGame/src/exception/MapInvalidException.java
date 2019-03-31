package exception;
// TODO: Auto-generated Javadoc

import java.io.Serializable;

/**
 * This class is used to handle Invalid Map Exceptions.
 *
 * @author yadavsurbhi
 * @version 1.0.0
 */

public class MapInvalidException extends Exception implements Serializable{

	/**
	 * Instantiates a new map invalid exception.
	 *
	 * @param errorMesssage Error message to be printed
	 */
	public MapInvalidException(String errorMesssage) {
		super(errorMesssage);
	}
}
