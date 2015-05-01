package org.cinvestav.locator.exceptions;

/**
 *
 * @author hector
 */
public class LocatorException extends Exception {

    /**
     * Creates a new instance of <code>LocatorException</code> without detail
     * message.
     */
    public LocatorException() {
    }

    /**
     * Constructs an instance of <code>LocatorException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public LocatorException(String msg) {
        super(msg);
    }
}
