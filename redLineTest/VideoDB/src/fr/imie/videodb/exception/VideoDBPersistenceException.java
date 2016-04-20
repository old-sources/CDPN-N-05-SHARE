/**
 * 
 */
package fr.imie.videodb.exception;

/**
 * @author imie
 *
 */
public class VideoDBPersistenceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3729321779516574209L;

	/**
	 * 
	 */
	public VideoDBPersistenceException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public VideoDBPersistenceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public VideoDBPersistenceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public VideoDBPersistenceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public VideoDBPersistenceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
