package exception;
/**This class is used to handle Invalid Map Exceptions
 * 
 * @author yadavsurbhi
 * @version 1.0.0
 */

public class MapInvalidException extends Exception {

	/**
	 * 
	 * @param errorMesssage Error message to be printed
	 */
	public MapInvalidException(String errorMesssage) {
		super(errorMesssage);
	}
}
